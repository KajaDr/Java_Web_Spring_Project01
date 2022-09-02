package pl.cyber.trainess.demo.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cyber.trainess.demo.service.KalkulatorService;

@RestController
@RequestMapping("/v1/basics")
public class BasicController {
    KalkulatorService kalkulatorService= new KalkulatorService();
    /*
  Zadanie 1
  Napisać zapytania dla prostego kalkulatora, który będzie obsługiwać 5 operacji
  (każda z nich powinna być oddzielnym zapytaniem restowym)
  Operacje:
  - dodawanie
  - odejmowanie
  - mnożenie
  - dzielenie
  - obliczenie reszty z dzielenia liczb
  Zwrócenie wyniku naszych operacji.

  Np
  "/dodawanie/{a}"

  @PathVariable
   */
@GetMapping("/dodawanie/{a}/{b}")
    public Integer getDodawanie(
            @PathVariable("a") final Integer liczbaA,
    @PathVariable("b") final Integer liczbaB){
    return  kalkulatorService.getDodawanie(liczbaA, liczbaB);
}
    @GetMapping("/dzielenie/{a}/{b}")
    public Integer getDzielenie(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB){
        return  kalkulatorService.getDzielenie(liczbaA, liczbaB);
    }
    @GetMapping("/odejmowanie/{a}/{b}") // "/odejmowanie- metoda/"
    public Integer getOdejmowanie(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB){
        return  kalkulatorService.getOdejmowanie(liczbaA, liczbaB);
    }
    @GetMapping("/mnozenie/{a}/{b}")
    public Integer getMnozenie(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB){
        return  kalkulatorService.getMnozenie(liczbaA, liczbaB);
    }
    @GetMapping("/modulo/{a}/{b}")
    public Integer getModulo(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB){
        return  kalkulatorService.getModulo(liczbaA, liczbaB);
    }
}
