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
}
