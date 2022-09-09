package pl.cyber.trainess.demo.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class StringRequest {
    // potrzeba nam obiektyu jasona
    // metody getter @Getter - utworzy nam metody dla getterow
    //1
    private final String stringPierwszy;
    private final String stringDrugi;
    //3 potrzebna adnotacja jesonowa - daje konstruktor jasonowy- odczytujacy informacje "body" ciała konstruktora
    @ConstructorProperties({"stringPierwszy", "stringDrugi"})
//2
    public StringRequest(final String stringPierwszy, final String stringDrugi) {
        this.stringPierwszy = stringPierwszy;
        this.stringDrugi = stringDrugi;
    }

}
