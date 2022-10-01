package pl.cyber.trainess.exam.service;

import lombok.Builder;
import org.springframework.stereotype.Service;
import pl.cyber.trainess.exam.dto.Liter2DTO;
import pl.cyber.trainess.exam.dto.Zliczanie;

import java.util.*;

@Service
@Builder
public class ExamService {
    private static String SPACJA1 = "";
    static List<Integer> liczby03 = new ArrayList<>();

    //region Zadanie 01
      /*

    Napisz zapytanie restowe, którego zadaniem będzie wykonywanie sprawdzenia czy przekazana liczba jest liczba pierwszą
    ( koncowka/ koncowka ReST/ endpoint)

    Instrukcji warunkowej sprawdzenia czy liczba <2
    pętla która bedzie sprawdzała poszczególne dzielniki i jeżeli który kolwiek 'modulo' zwrocic 0 to nie jest to liczba pierwsza"
     */
    public String getzad2(final Integer a) {
        if (a < 2) {
            return "to nie liczba pierwsza";
        }
        for (int i = 2; i < a; i++) {
            if (a % i == 0)
                return "to nie jest liczba pierwsza";
        }
        return "to liczba pierwsza";
    }
    //endregion

    //region Zad02
    public String zad3(final Integer a, final Integer b) {

          /*
  Napisz program, w którym zostaną przekazane liczby a i b (całkowite) następnie
  zostaną zsumowane wszystkie liczby pomiędzy od a do b
  (jako przedział zamknięty dwustronnie).
  Przykład podajemy: 1 do 10 czego wynikiem będzie 55
  // GET PathVariable lub RequestBody - jak pasuje
  //basicsService
  */
        Integer suma = 0;
        if (a < b) {
            liczby03.add(a);
            for (int i = a + 1; i < b; i++) {
                liczby03.add(i);
                suma = +i + suma;
            }
            liczby03.add(b);
        } else {
            liczby03.add(b);
            for (int i = b + 1; i < a; i++) {
                liczby03.add(i);
                suma = +i + suma;
            }
            liczby03.add(a);
        }

        return liczby03 + "\n" +
                "wartość sumy: " + suma;
    }
    //endregion

    //    region Zadanie3
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
    public List<String> getZdanie(final Zliczanie request) {
        List<Liter2DTO> literyKtorewystepuja = new ArrayList<>();
        Set<String> literki = new HashSet<>();
        List<String> wynik = new ArrayList<>();

        String zdanie = request.getValue();

        for (int i = 0; i < zdanie.length(); i++) {
            String literka = String.valueOf(zdanie.charAt(i));
            if (literka.matches("[a-zA-Z]]+")) {
                if (literyKtorewystepuja.size() == 0) {
                    literki.add(literka.toLowerCase());
                    literyKtorewystepuja.add(new Liter2DTO(literka.toLowerCase(), 1));
                } else {
                    if (literki.contains(literka.toLowerCase())) {
                        for (Liter2DTO el : literyKtorewystepuja
                        ) { if (el.getLitera2().equals(literka.toLowerCase())){
                            el.setIlosc2(el.getIlosc2()+1);
                        }
                        }
                    }else {
                        literki.add(literka.toLowerCase());
                        literyKtorewystepuja.add(Liter2DTO.builder()
                                .litera2(literka.toLowerCase())
                                .ilosc2(1)
                                .build());
                    }
                }
            }


        }
        for (Liter2DTO ele: literyKtorewystepuja){
            wynik.add(ele.getLitera2()+ "-"+ele.getIlosc2());
        }
        wynik.sort(String::compareTo);


        return wynik;
    }
    //endregion
}
