package pl.cyber.trainess.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.demo.dto.ListaRequest;
import pl.cyber.trainess.demo.dto.OneStringRequest;
import pl.cyber.trainess.demo.dto.RozwiazanieRownaniaRequest;
import pl.cyber.trainess.demo.dto.StringRequest;
import pl.cyber.trainess.demo.service.BasicsService;
import pl.cyber.trainess.demo.service.KalkulatorService;
import pl.cyber.trainess.demo.service.PDbasicService;
import pl.cyber.trainess.demo.service.ZnajdzService;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequestMapping("/v1/basics") // mapowanie do postmana // Tomcat started on port(s): >> 8150 (http)<< with context path '/demo'
@RequiredArgsConstructor // daje mozliwosc przywolania odpowiedniej klasy
public class BasicController {
    KalkulatorService kalkulatorService = new KalkulatorService(); // zła praktyka, mozna ja stosowac w klasach Service
    private final BasicsService basicsService; // działa dzieki : @RequiredArgsConstructor

    private final ZnajdzService znajdzLiczbe;
private  final PDbasicService  pDbasicService;

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
            @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getDodawanie(liczbaA, liczbaB);
    }

    @GetMapping("/dodawanieParams")
    public Integer getDodawanieParams(
            @RequestParam("a") final Integer liczbaA, //ukrytosc, nie jest definiowane bezzposrednio
            @RequestParam("b") final Integer liczbaB) { // http://localhost:8150/demo/v1/basics/dodawanieParams?a=100&b=200
        return kalkulatorService.getDodawanie(liczbaA, liczbaB);
    }

    @GetMapping("/dzielenie/{a}/{b}")
    public Integer getDzielenie(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getDzielenie(liczbaA, liczbaB);
    }

    @GetMapping("/odejmowanie/{a}/{b}") // "/odejmowanie- metoda/"
    public Integer getOdejmowanie(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getOdejmowanie(liczbaA, liczbaB);
    }

    @GetMapping("/mnozenie/{a}/{b}") // zapytanie restowe czyli mapowanie na HTTP
    public Integer getMnozenie(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getMnozenie(liczbaA, liczbaB);
    }

    @GetMapping("/modulo/{a}/{b}")
    public Integer getModulo(
            @PathVariable("a") final Integer liczbaA,
            @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getModulo(liczbaA, liczbaB);
    }
//region Zadanie 02
    /*

    Napisz zapytanie restowe, którego zadaniem będzie wykonywanie sprawdzenia czy przekazana liczba jest liczba pierwszą
    ( koncowka/ koncowka ReST/ endpoint)

    Instrukcji warunkowej sprawdzenia czy liczba <2
    pętla która bedzie sprawdzała poszczególne dzielniki i jeżeli który kolwiek 'modulo' zwrocic 0 to nie jest to liczba pierwsza"
     */
    @GetMapping("/zadanie2/{a}") // do wyszukiwarki : http://localhost:8150/demo/v1/basics/zadanie2/147
    public String getLiczbaPierwsza(
            @PathVariable("a") final Integer a) {
        return kalkulatorService.getLiczbaPierwsza(a);
    }
    //endregion

    //region zad 03
    /*
    Napisz zapytanie restowe, którego zadaniem bedzie wykonanie sklejenia dwóch Stringów ( przekazanych jako requestBody) a nastepnie zwroci wynik
    POST/PUT
     */
    @PostMapping("/zsklejenie-stringow/") // do wyszukiwarki : http://localhost:8150/demo/v1/basics/zadanie2/147
    public String getSklejenie(
            @RequestBody final StringRequest request) {// alt+ enter
        return basicsService.getSklejenie(request);
    }

    //endregion
    //    region Zadanie4
/*Napisz zapytanie restowe, którego zadaniem będzie przyjęcie napisu  jako zdania
(przekazanych jako RequestBody)
 Program powinien policzyć ilość wystąpień poszczególnych liter zdania i zwrócić
 odpowiednio przygotowane dane.
Uwaga należy pominąć litery, których w zdaniu nie ma oraz wszystkie znaki puste.

Przykład.
Ala ma kota

a - 4
k - 1
l - 1
m - 1
o - 1
t - 1
*/
    /*
    @POSTMapping
    @RequestBody

    List<LiteryDTO> wystapienia - gromadzi wszystkie litery
    Set<String> litery - kolekcja niepowtarzajacych sie liter
    List<String> wynik

    petla for przejscie po kzadym znaku zdania
    if jesli znak ze zdania jest litera to nalezy wykonac dodawanie lub aktualizacje naszej litery

    Object String posiada metodę matches(//reqexp//) <- mozna porównywać wiele rzeczy  .matches("[a-zA-Z]+") - porownywanie rzeczy od a do Z

    Object List posiada metodę sort (//Comarator//)  .sort(String::compareTo)
     */
    @PostMapping("/zliczanie")
    public List<String> getWystapieniaLiterWZdaniu(
            @RequestBody final OneStringRequest request // metoda http
    ) {
        return basicsService.getWystapieniaLiterWZdaniuMap(request);
    }
    //endregion
    //region Zadanie 5
    /*
    Napisz zapytanie restowe, którego zadaniem będzie przekierowanie liczb a i b ( całkowite) nastepnie
    wykona sprawdzenie czy liczba a jest dzielnikiem liczby b i zwroci informacje true lub false
     */

    @PostMapping("/dzielnik- liczby/{a}/{b}")
    public String getDzielnik(
            @PathVariable("a") final Integer a,
            @PathVariable("b") final Integer b ){
                return kalkulatorService.getDzielnik(a,b);
    }

    @PostMapping("/dzielnik- liczby2")
    public String getDzielnik2(
            @RequestParam("a") final Integer a,
            @RequestParam("b") final Integer b ){
        return kalkulatorService.getDzielnik(a,b);
    }

    @PostMapping ("/zgadnij-liczbe")
    public String getZgadnijLiczbe(
            @RequestParam ("a") final Integer a
    ){
        return znajdzLiczbe.getLiczba(a);
    }

    //endregion
    //region Zadanie7
  /*
  Napisz program, w którym zostaną przekazane liczby a i b (całkowite) następnie
  zostaną zsumowane wszystkie liczby pomiędzy od a do b
  (jako przedział zamknięty dwustronnie).
  Przykład podajemy: 1 do 10 czego wynikiem będzie 55
  // GET PathVariable lub RequestBody - jak pasuje
  //basicsService
  */
    @GetMapping("/zadanie-7/{a}/{b}")
    public Integer getZadanie7(
            @PathVariable final Integer a,
            @PathVariable final Integer b){
        return pDbasicService.sumaLiczb(a,b);
    }


    //besicsService
    //endregion


 //robienie folderu //region na poczatku
    // endregion + CTRL +.

    //region Zadanie8
  /*Napisz program, w krótym przekażemy listę elementów liczb całkowitych program
  powinien zwrócić listę elementów z wartościami ujemnymi oraz sumę liczb,
  które są dodatnie.
  Np. [1, 2, 3, 4, 5, -3, -2, -1]
  wynik:
  [-3, -2, -1] oraz suma liczb dodatnich wynosi: 15

  listaUjemne + " xxxxx" + sumaDodat

  //besicsService
  //POST RequestBody
  //IntegerListRequest >>> List<Integer
  { JASON
  "lista": [1,2,3,4,5,6,7,8,]
  }

  if do sprawdzenia czy liczba z listy jest ujemna czy jest dodatnia
 .sort do sortowania listy
  */
    @PostMapping("/zadanie-8")
    public String getZadanie8(
            @RequestBody final ListaRequest request)
    {
        return pDbasicService.getZadanie8(request);
    }

    //endregion

    //region zadanie9
    /*
    Napisz zapytanie restowe, którego zadaniem będzie obliczał pierwiastek
    równania kwadratowego ax2 + bx + c = 0.
    (Do wykożystania instrukcja if). Pamiętać należy że zmienne a, b i c to
    liczby rzeczywiste.
    Zadanie powinno zwrócić Napis:
    a) To nie jest równanie kwadratowe
    b) Brak pierwiastków
    c) J
eden pierwiastek. Wynik: xxxx
    d) Dwa pierwiastki. Wynik -> x1: xxxx, x2: xxxx

    //besicsService
    //GET PathVarieble
    //GET  Request param
    // Post RequestBody ( z własnym obiektem DTO)
     */

    @GetMapping("/zadanie-9/{a}/{b}/{c}")
        public String getZadanie9(
                @PathVariable("a") final Integer a,
                @PathVariable("b") final Integer b,
                @PathVariable("c") final Integer c){
        return pDbasicService.getZadanie9(a,b,c);
    }

    @PostMapping("/zadanie-9a")
    public String getZadanie9a(
            @RequestBody final RozwiazanieRownaniaRequest request) {
        return pDbasicService.rozwiazanieRownania(request);
    }

    @PostMapping("/zadanie-9a-zaokraglenia")
    public String getZadanie9a(
            @RequestBody final RozwiazanieRownaniaRequest2 request) {
        return pDbasicService.rozwiazanieRownaniaZaokraglenia(request);
    }
    //KalkulatorService
    //endregion

}
