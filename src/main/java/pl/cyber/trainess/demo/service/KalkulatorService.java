package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;

@Service
public class KalkulatorService {
    //metoda dodawania
    public Integer getDodawanie(final Integer a, final Integer b) {
        return a + b;
    }

    // metoda dzielenia
    public Integer getDzielenie(final Integer a, final Integer b) {
        return a / b;
    }

    //metoda odejmowania
    public Integer getOdejmowanie(final Integer a, final Integer b) {
        if (a != 0) {
            return a / b;
        } else {
            throw new RuntimeException("  nie dziel przez zero");
        }
        /*
        if ( a== 0){ throw new  RuntimeException}
         return a/b;
         }
         */
    }

    // metoda mnożenia
    public Integer getMnozenie(final Integer a, final Integer b) {
        return a * b;
    }

    // metoda obliczenia reszty z dzielenia liczb
    public Integer getModulo(final Integer a, final Integer b) {
        return a % b;
    }

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
        if (b == 0) {
            throw new RuntimeException("nie dziel przez zero");
        }
        //return a%b==0 - wersja true false
        else if (a % b == 0) {
            return "jest dzielnikiem";
        } else {
            return "nie jest dzielnikiem";
        }
    }

    public String zadanie10a() {
//        Integer y = 0;
//        String result = " Program oblicza wartosc funkcji y=3x, dla x zmieniajacego sie od 0 do 10.\n";
//
//        for (int x = 0; x <= 10; x++) {
//            y = 3 * x;
//            result += "x=" + x + "\t" + "y = " + y+"\n";
//        }
//        return result;
//
        Integer y = 0;
        StringBuilder result = new StringBuilder(" Program oblicza wartosc funkcji y=3x, dla x zmieniajacego sie od 0 do 10.\n"); // przyspiesza proces obliczeniowy

        for (int x = 0; x <= 10; x++) {
            y = 3 * x;
            result.append("x=").append(x).append("\t").append("y = ").append(y).append("\n"); // przez string buildera
        }
        return result.toString();
    }

    public String zadanie10b() {
        Integer x = 0;
        Integer y = 0;

        String result = (" Program oblicza wartosc funkcji y=3x, dla x zmieniajacego sie od 0 do 10.\n" + "za pomoca petli do ... while\n");
        do {
            y = 3 * x;
            result += "x= " + x + "\t" + " y " + y + "\n";
            x++;
        } while (x <= 10);// warunek sprawdzajacy
        return result;
    }

    public String zadanie10c() {
        Integer x = 0;
        Integer y = 0;

        String result = (" Program oblicza wartosc funkcji y=3x, dla x zmieniajacego sie od 0 do 10.\n"
                + "za pomoca petli while\n");
        while (x <= 10) {
            y = 3 * x;
            result += "x= " + x + "\t" + " y " + y + "\n";
            x++;
        }
        return result;
    }


    // aby sprawdzic czy działa mozna użyc debuggera, zaznaczajac checkpointy, oraz evaluate expression < zaznaczanie  ekspresji do sprawdzenia>
    /*
    public Integer getLiczbaPierwsza(final Integer liczbaA) {
if ( a ==0) { throw new RuntimeException("Liczba nie mote by6 0 ");
else if (a== 1) { throw new RuntimeException("Liczba nie mote by6 +-1 ");
else if (a % 2 == 0) throw new RuntimeException("Liczba nie mote by6 parzysta ");
else if a % a != 0 && a % 1 != 0) f
     */

    public String zadanie11a() {
        Integer n = 10;

        String result = " Program wyswietla tabliczke mnozenia \n\n";

        for (int wiersz=1; wiersz <= n; wiersz++) {
            for (int kolumna = 1; kolumna <= n; kolumna++) {
                result += wiersz*kolumna +"\t";
            }
            result+="\n";
        }
        return result;

    }
    public String zadanie11b() {
        Integer w = 10;
        Integer k = 15;

        String result = " Program wyswietla tabliczke mnozenia \n\n";

        for (int wiersz=1; wiersz <= w; wiersz++) {
            for (int kolumna = 1; kolumna <= k; kolumna++) {
                result += wiersz*kolumna +"\t";
            }
            result+="\n";
        }
        return result;

    }

    public String zadanie11c() {
        Integer w = 10;
        Integer k = 10;
        Integer kolumna=1;
        Integer wiersz=1;

        String result= " Program wyswietla tabliczke mnozenia \n\n";
        do {
            kolumna=1;
            do {
                result+=kolumna*wiersz;
                result+="\t";
                kolumna++;
            }while (kolumna<=k );
            result+="\n";
            wiersz ++;
        }while (wiersz<=w);

        return result;
    }

    public String zadanie11d() {
        Integer w = 10;
        Integer k = 10;
        Integer kolumna=1;
        Integer wiersz=1;
        String result= " Program wyswietla tabliczke mnozenia \n\n";
        while (wiersz<=w){
            kolumna=1;
            while(kolumna<=k ){
                result+=wiersz*kolumna;
                result+="\t";
                kolumna ++;
            }
            result+="\n";
            wiersz++;
        }

        return result;

    }
}