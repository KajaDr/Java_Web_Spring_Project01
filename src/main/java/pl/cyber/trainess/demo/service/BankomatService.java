package pl.cyber.trainess.demo.service;

import groovy.text.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.config.Templates;
import pl.cyber.trainess.demo.domain.BankomatEntry;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.repository.BankomatRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

@Slf4j // do weryfikacji - to sa te log'i na dole
@Service
@RequiredArgsConstructor
public class BankomatService {
    private final BankomatRepository bankomatRepository;
    private final FileReaderService fileReaderService;

    public List<BankomatDTO> getAllAtms() {
        /*
        Połączenie do Db oraz pobranie odpowiednich informacji
        2. przygotowanie lity wynikowej
        3. Petla konwetyujaca obiekt DB na obiekt dla uzytkownika
         */
        log.info("wyszukanie wszystkich bankomatow");
        log.warn("cos poszło nie tak");
        log.error("rest communication failed"); //kiedy łaczy sie z zewnetrza aplikacjado pobrania danych, jak nastepuje bład

        var allAtms = bankomatRepository.findAll();
        List<BankomatDTO> result = new ArrayList<>();

        for (BankomatEntry entry : allAtms) {
            result.add(entry.converToDTO());
        }
        return result;
    }

    public void create(final BankomatDTO bankomatDTO) {
        bankomatRepository.save(BankomatEntry.builder()
                .miasto(bankomatDTO.getMiasto())
                .czyAktywny(bankomatDTO.getCzyAktywny())
                .name(bankomatDTO.getName())
                .saldo(bankomatDTO.getSaldo())
                .ulica(bankomatDTO.getUlica())
                .build()); // kolejnosc parametrow jest nie wazna, zalatwia to builder
    }

    public void create(final MultipartFile file) {
        List<BankomatDTO> bankomatDTOS = fileReaderService.readATMFile(file);
        for (BankomatDTO element : bankomatDTOS) {
            bankomatRepository.save(BankomatEntry.builder()
                    .name(element.getName())
                    .saldo(element.getSaldo())
                    .miasto(element.getMiasto())
                    .ulica(element.getUlica())
                    .czyAktywny(element.getCzyAktywny())
                    .build());
        }
    }

    @Transactional
    public void updateName(final String id, final String name) { // jesli chcemy miec dostep do zmieniania parametrow
//  1)      var allAtms = bankomatRepository.findAll();
//        for (BankomatEntry entry: allAtms){
//            if (entry.getId().equals(id)){
//                entry.setName(name);
//                bankomatRepository.save(entry);
//        } opcja niezalecana bardziej obciąża komputer

//    2)     var atm=  bankomatRepository.findById(id)
//                 .orElseThrow(()->new RuntimeException("brak rekordu"));

//    3)    var atm=  bankomatRepository.findById(id)
//                .ifPresent(entry-> {
//                    entry.setName(name);
//                    bankomatRepository.save(entry);
//                });
//4)
        bankomatRepository.findById(id)
                .ifPresentOrElse(entry -> {
                            entry.setName(name);
                            bankomatRepository.save(entry);
                        },
                        () -> {
                            throw new RuntimeException("brak rekordu");
                        });
    }


    //    5)
//        var atm=  bankomatRepository.findById(id)
//                .orElse(null);
//        if(Objects.nonNull(atm)){
//            atm.setName(name);
//            bankomatRepository.save(atm);
//        }
    //6
//bankomatRepository.updateName(id,name);
    @PostMapping("/wplata")
    public void wplata(
            final String id,
            final Integer cash) {
        if (cash <= 0) {
            throw new RuntimeException("Niedowzwolona kwota wpłaty");
        }
        var atm = bankomatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Nie odnaleziono bankomatu")));
        atm.setSaldo(BigDecimal.valueOf(cash));
        bankomatRepository.save(atm);
        if (atm.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
            atm.setCzyAktywny(true);
        }
    }


    public void wyplata(final String id, final Integer cash) {
        if (cash <= 0) {
            var atm = bankomatRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("nie odnaleziono bankomatu"));

            if ((atm.getSaldo().subtract(BigDecimal.valueOf(cash))).compareTo(BigDecimal.ZERO) < 0) { //pozwala sprawdzic czy wyplacona kwota, jest mozliwa
                // ( odejmuje sie kwote pozniej porownuje do zera lub wartosci ujemnej) jesli 0 lub -1 to  rzuca wyjatkiem -> get saldo(100).substract(-200).compareto(-1<- rzuca wyjatkiem bo jest true)
                throw new RuntimeException("Kwota wyplaty jest wieksza niz gotowka");
            }
            atm.setSaldo(atm.getSaldo().subtract(BigDecimal.valueOf(cash)));

            if (atm.getSaldo().compareTo(BigDecimal.ZERO) > 0)
                atm.setCzyAktywny(false);
            bankomatRepository.save(atm);
        }
    }

    public void getBankomatPDFile(final HttpServletResponse response) throws DRException {
        final JasperReportBuilder reportBuilder = getReportContextPrepare();
        response.setContentType("application/pdf");
        response.setCharacterEncoding(CharEncoding.UTF_8);
        setDefaultReportProperties(reportBuilder);

//        response.getOutputStream().write(JasperExportManager.exportReportToPdf(reportBuilder.toJasperPrint()))
        ;//output stream cos wychodzi z aplikaji
//        setDefaultReportProperties(reportBuilder);

        try {
            response.getOutputStream().write(JasperExportManager.exportReportToPdf(reportBuilder.toJasperPrint()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }
private JasperReportBuilder getReportContextPrepare(){
        JasperReportBuilder jrBuilder;
        List<BankomatEntry>bankomatEntryList=bankomatRepository.findAll();
        List<BankomatDTO>bankomatList= new ArrayList<>();

    for (BankomatEntry element:bankomatEntryList
         ) {bankomatList.add((toReport(element)));
        bankomatList.add(element.converToDTO());

    }

    StyleBuilder textStyle=stl.style(Templates.columnStyle)
            .setBorder(stl.pen1Point());

    TextColumnBuilder<String>idColumn= col.column("DB","id",type.stringType());
    TextColumnBuilder<String>nameColumn=col.column("Bankomat Name","name", type.stringType());
    TextColumnBuilder<BigDecimal>saldoColumn=col.column("Saldo","saldo", type.bigDecimalType());
    TextColumnBuilder<String>miastoColumn=col.column("Miasto", "miasto", type.stringType());
    TextColumnBuilder<String>ulicaColumn=col.column("Ulica", "ulica", type.stringType());
    TextColumnBuilder<Boolean>czyAktywneColumn=col.column("Aktywny?", "czyAktywny", type.booleanType());
    jrBuilder=report().setTemplate(Templates.reportTemplate)
            .setColumnStyle(textStyle)
            .columns(idColumn,nameColumn,saldoColumn,miastoColumn,ulicaColumn,czyAktywneColumn)
            .title(Templates.createTitleComponent("Raport bankomatow"))
            .setDataSource(bankomatRepository.findAll() // wyszukuje liste encji poprzez wewnetrzny obiekt
            .stream() //otwarcie watku  ktory bedzie przetwarzany, zastepuje petle
            .map(this:: toReport) // ::wywołanie metody ktora jest zdefiniowana w ramach naszej klasy
                            //       .map(BankomatEntry::converToDTO) alternatywa
                            // wykonujemy metode toReport z klasy BankomatService
                    //konstrukcja this::toReport odwołuje się do klasy w której jest ten kod napisany
                    //oraz próbuje odszukac metode  o nazwie toReport
                    //.map(bankomatList)
            .collect(Collectors.toList())// ma za zadanie tworzyc z tego liste
            );

            return jrBuilder;

}
    private BankomatDTO toReport(final BankomatEntry bankomatEntry) {
        return BankomatDTO.builder()
                .id(bankomatEntry.getId())
                .name(bankomatEntry.getName())
                .saldo(bankomatEntry.getSaldo())
                .miasto(bankomatEntry.getMiasto())
                .ulica(bankomatEntry.getUlica())
                .czyAktywny(bankomatEntry.getCzyAktywny())
                .build();
    }// converter -arto zrobic w osobnej klasie aby sie nie baboliło

    private void setDefaultReportProperties( final JasperReportBuilder reportBuilder ){
        reportBuilder.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
        reportBuilder.pageFooter(cmp.pageXofY());
        reportBuilder.setColumnTitleStyle(stl.style(stl.style(stl.style().bold())
                        .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
                .setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY));
        reportBuilder.setDetailSplitType(SplitType.PREVENT);
        reportBuilder.setColumnStyle(stl.style()
                .setHorizontalTextAlignment(HorizontalTextAlignment.JUSTIFIED));
        reportBuilder.highlightDetailOddRows();
    }
}


