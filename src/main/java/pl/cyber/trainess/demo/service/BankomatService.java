package pl.cyber.trainess.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.domain.BankomatEntry;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.repository.BankomatRepository;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j // do weryfikacji - to sa te log'i na dole
@Service
@RequiredArgsConstructor
public class BankomatService {
    private final BankomatRepository bankomatRepository;
    private final FileReaderService fileReaderService;
    public List<BankomatDTO> getAllAtms(){
        /*
        Połączenie do Db oraz pobranie odpowiednich informacji
        2. przygotowanie lity wynikowej
        3. Petla konwetyujaca obiekt DB na obiekt dla uzytkownika
         */
        log.info("wyszukanie wszystkich bankomatow");
        log.warn("cos poszło nie tak");
        log.error("rest communication failed"); //kiedy łaczy sie z zewnetrza aplikacjado pobrania danych, jak nastepuje bład

        var allAtms= bankomatRepository.findAll();
        List<BankomatDTO> result = new ArrayList<>();

        for (BankomatEntry entry: allAtms){
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
            .ifPresentOrElse(entry-> {
                entry.setName(name);
                bankomatRepository.save(entry);
            },
                    ()-> {
                throw new RuntimeException( "brak rekordu");
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

}

