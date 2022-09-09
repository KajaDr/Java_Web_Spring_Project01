package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;

@Service
public class KalkulatorService {
    //metoda dodawania
    public Integer getDodawanie(final Integer a, final Integer b){
        return a+b;
    }
    // metoda dzielenia
    public Integer getDzielenie(final Integer a, final Integer b){
        return a/b;
    }
    //metoda odejmowania
    public Integer getOdejmowanie(final Integer a, final Integer b){
        if (a!=0) {
            return a / b;
        } else{
            throw new RuntimeException( "  nie dziel przez zero");
        }
        /*
        if ( a== 0){ throw new  RuntimeException}
         return a/b;
         }
         */
        }
    // metoda mnożenia
    public Integer getMnozenie(final Integer a, final Integer b){
        return a*b;}
    // metoda obliczenia reszty z dzielenia liczb
    public Integer getModulo(final Integer a, final Integer b){
        return a%b;}

    public String getLiczbaPierwsza(final Integer a) {
        if (a < 2) {
            return "to nie liczba parzysta";
        }
        for (int i = 2; i <= a / 2; i++) { //  i= 2 od czego zaczynamy, do kiedy- warunek, w ktora strone
            if (a % i == 0) {
                return " to nie liczba pierwsza";
            }
        }
            return " to liczba pierwsza";
        }

    public String getDzielnik(final Integer a, final Integer b) {
        if(b ==0){
            throw  new RuntimeException("nie dziel przez zero");
        }
        //return a%b==0 - wersja true false
        else if (a%b==0){
            return "jest dzielnikiem";
        }else {
            return "nie jest dzielnikiem";
        }
    }
}
    // aby sprawdzic czy działa mozna użyc debuggera, zaznaczajac checkpointy, oraz evaluate expression < zaznaczanie  ekspresji do sprawdzenia>
    /*
    public Integer getLiczbaPierwsza(final Integer liczbaA) {
if ( a ==0) { throw new RuntimeException("Liczba nie mote by6 0 ");
else if (a== 1) { throw new RuntimeException("Liczba nie mote by6 +-1 ");
else if (a % 2 == 0) throw new RuntimeException("Liczba nie mote by6 parzysta ");
else if a % a != 0 && a % 1 != 0) f
     */

