package pl.cyber.trainess.demo.endpoint;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.service.BankomatService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController // odbiera wiadomosci/informacje z zewnatrz
@RequiredArgsConstructor //// tworza konstruktor, utworzy konstrukto z wymaganych parametrow
//@AllArgsConstructor // tworza konstruktor, utworzy konstrukto z wszystkich parametrow
@RequestMapping("/v1/atms") // informacja o domenie wewnetrznejs z ktorego program bedzie nasluchiwal
public class BankomatController {
    private final BankomatService bankomatService;

    @GetMapping
    public List<BankomatDTO> getAllAtms() {
        return bankomatService.getAllAtms();
    }

    @PutMapping
    public void create(@RequestBody final BankomatDTO bankomatDTO) {
        bankomatService.create(bankomatDTO); // metoda zapisujaca dane do bazy
    }

    //spring security nowy temat
    @PostMapping("/name")
    public void updateName(
            @RequestParam("name") final String name,
            @RequestParam("id") final String id) {
        bankomatService.updateName(id, name);
    }

    //MultipartFile
    @PostMapping("/import-csv")
    public void createFromCSV(
            @RequestPart() MultipartFile file
    ) {
        bankomatService.create(file);
    }

    @PostMapping("/wplata")
    public void wplata(
            @RequestParam("id") final String id,
            @RequestParam("cash") final Integer cash) {
        bankomatService.wplata(id, cash);
    }

    @PostMapping("/wyplata")
    public void wyplata(
            final String id,
            @RequestParam("cash") final Integer cash) {
        bankomatService.wyplata(id, cash);
    }
    @PostMapping("/pdf")
    public void getBankomatPDFile(HttpServletResponse response) throws DRException {
        bankomatService.getBankomatPDFile(response);
    }
}