package pl.cyber.trainess.exam.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.exam.dto.Zliczanie;
import pl.cyber.trainess.exam.service.ExamService;

import java.util.List;

@RestController
@RequestMapping("/v2/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    //region Zadanie 01
    /*

    Napisz zapytanie restowe, którego zadaniem będzie wykonywanie sprawdzenia czy przekazana liczba jest liczba pierwszą
    ( koncowka/ koncowka ReST/ endpoint)

    Instrukcji warunkowej sprawdzenia czy liczba <2
    pętla która bedzie sprawdzała poszczególne dzielniki i jeżeli który kolwiek 'modulo' zwrocic 0 to nie jest to liczba pierwsza"
     */
@GetMapping("/zad2/{a}")
    public String getzad2(
            @PathVariable ("a") final Integer a){
    return examService.getzad2(a);
}

    //endregion

    //region Zadanie 02
  /*
  Napisz program, w którym zostaną przekazane liczby a i b (całkowite) następnie
  zostaną zsumowane wszystkie liczby pomiędzy od a do b
  (jako przedział zamknięty dwustronnie).
  Przykład podajemy: 1 do 10 czego wynikiem będzie 55
  // GET PathVariable lub RequestBody - jak pasuje
  //basicsService
  */
    @GetMapping("/zadanie3/{a}/{b}")
    public String getZadanie3(
            @PathVariable final Integer a,
            @PathVariable final  Integer b) {
        return examService.zad3(a,b);
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
    @PostMapping("/zdanie")
    public List<String> getZdanie (
            @RequestBody final Zliczanie request){
        return examService.getZdanie(request);
    }
    //endregion
}
