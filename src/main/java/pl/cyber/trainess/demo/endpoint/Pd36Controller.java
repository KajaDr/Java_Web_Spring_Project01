package pl.cyber.trainess.demo.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.demo.service.Pd36RozwiazanieService;
@Slf4j
@RestController
@RequestMapping("/v2/pd36")
@RequiredArgsConstructor
public class Pd36Controller {


    private final Pd36RozwiazanieService pd36;
    //region ZADANIE DOMOWE 1
  /*
zadanie
  Proszę zadanie 10 (a, b, c) przerobić tak, aby można było z zewnątrz podać parametr x oraz parametr do kiedy nasza pętla ma się wykonywać
  Proszę aby nie wzorować się na dostępnych metodach.

   -----------
    Za pomoca instrukcji petli for dla danych wartosci x zmieniający sie od 0 do 10 obliczamy wartości funkcji y=3x
    */
    @PostMapping("/zadaniePD36a/")
    public String getWartoscXa(
            @RequestParam("a")  Integer  a,
            @RequestParam("x") final Integer  x){
        return pd36.zadanie1a(x, a);
    }
    @PostMapping("/zadaniePD36b/")
    public String getWartoscXb(
            @RequestParam("a")  Integer  a,
            @RequestParam("x") final Integer  x){
        return pd36.zadanie1b(x, a);
    }

    @PostMapping("/zadaniePD36c/")
    public String getWartoscXc(
            @RequestParam("a")  Integer  a,
            @RequestParam("x") final Integer  x){
        return pd36.zadanie1c(x, a);
    }
    //endregion

    //region ZADANIE DOMOWE 2
  /*
1)
Napisz program, który oblicza wartość x z równania ax+b = c. Wartości a, b i c należy podać poprzez PathVariable, RequestParam lub RequestBody.
Należy zabezpieczyć program na wypadek sytuacji, kiedy wprowadzona wartość 'a' będzie równa zero. Dla zmiennych a, b, c oraz x należy
przyjąć format wyświetlania ich na ekranie z dokładnością do dwóch miejsc
po przecinku
*/
    @PostMapping("/zadaniePD36-2/{a}/{b}/{c}")
    public String getZadanie2(
            @PathVariable ("a") final Double a,
            @PathVariable ("b") final Double b,
            @PathVariable ("c") final Double c){
        return pd36.zadanie2(a,b,c);
    }
    //endregion 2
    /*
//region ZADANIE DOMOWE 3
Napisz program, który za pomocą instrukcji (for, do ... while oraz while, tzn trzy różne rozwiązania)
znajduje największą i najmniejszą liczbę ze zbioru 'n' wylosowanych liczb całkowitych od 0 do 100
oraz oblicza średnią ze wszystkich wylosowanych liczb
  */
    @PostMapping("/zadaniePD-3a/")
    public String getWartosc3a(
            @RequestParam("a") final Integer  a){
        return pd36.zadanie3a(a);
    }
    @PostMapping("/zadaniePD-3b/")
    public String getWartosc3b(
            @RequestParam("a") final Integer  a){
        return pd36.zadanie3b(a);
    }
    //endregion 2



}
