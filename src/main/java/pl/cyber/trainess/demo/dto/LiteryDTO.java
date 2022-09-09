package pl.cyber.trainess.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // adnotacja tworzaca metody
@Builder// adnotacja pomagajaca budujaca obiekty w oparciu o constructor klasy
/*
new LiteryDto("a",1)
builder działa na zasadzie:
LiteryDtO.builder()
.litera("a")
.ilosc(1)
.build()

litera-> a, jej ilosc ->1
-----------------------------
LiteryDtO.builder()
.litera("a")
.build()

litera-> a, jej ilosc ->null
-> builder ukrya niektore informacje
 */
@AllArgsConstructor// adnotacja buduje z wszystkich (tutaj dwóch) parametrow konstruktor w naszej klasie

public class LiteryDTO {
    private String litera;
    private Integer ilosc;

}
