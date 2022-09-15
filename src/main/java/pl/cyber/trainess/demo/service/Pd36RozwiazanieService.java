package pl.cyber.trainess.demo.service;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.compare;
import static java.lang.Integer.getInteger;


@Service
public class Pd36RozwiazanieService {
    public String zadanie1a(Integer x, Integer a) {
        // y=3x
        Integer y = 0;
        StringBuilder result = new StringBuilder("Program oblicza y=3x");
        if (x == 0) {
            throw new RuntimeException("podaj inna wartosc niz zero");
        } else if (a < x) {
            throw new RuntimeException(" wartosc a musi byc wieksza od wartosci x");
        }
        for (int i = x; i <= a; i++) {
            for (x = i; x <= i; x++) {
                y = 3 * x;
                result.append("\nx: ").append(x).append("\t").append("y = ").append(y).append("\n");
            }
        }
        return result.toString();
    }

    public String zadanie1b(Integer x, final Integer a) {
        Integer y = 0;
        StringBuilder result = new StringBuilder((" Program oblicza wartosc funkcji y=3x, dla x zmieniajacego sie od 0 do 10.\n"
                + "za pomoca petli while\n"));
        if (x == 0) {
            throw new RuntimeException("podaj inna wartosc niz zero");
        } else if (a < x) {
            throw new RuntimeException(" wartosc a musi byc wieksza od wartosci x");
        }
        do {
            y = 3 * x;
            result.append("\nx: ").append(x).append("\t").append("y = ").append(y).append("\n");
            x++;


        } while (x <= a);
        return result.toString();
    }

    public String zadanie1c(Integer x, final Integer a) {
        Integer y = 0;
        StringBuilder result = new StringBuilder((" Program oblicza wartosc funkcji y=3x, dla x zmieniajacego sie od 0 do 10.\n"
                + "za pomoca petli while\n"));
        if (x == 0) {
            throw new RuntimeException("podaj inna wartosc niz zero");
        } else if (a < x) {
            throw new RuntimeException(" wartosc a musi byc wieksza od wartosci x");
        }
        while (x <= a) {
            y = 3 * x;
            result.append("\nx: ").append(x).append("\t").append("y = ").append(y).append("\n");
            x++;
        }
        return result.toString();
    }

    public String zadanie2(final Double a, final Double b, final Double c) {

        Locale polLoc = Locale.forLanguageTag("pl-PL");
        Locale.setDefault(polLoc);
        DecimalFormat df = new DecimalFormat("#.##");

        Double x;
        StringBuilder result = new StringBuilder("program, który oblicza wartość x z równania ax+b = c");

        if (a == 0) {
            throw new RuntimeException("Zla wartosc, powinna być inna niz 0");
        } else {
            x =(c-b)/a ;
            result.append("\t " + " x = ").append(df.format(x));

        }
        return result.toString();
    }

    public String zadanie3a(final Integer a) {
        List<Integer> lista = new ArrayList();

        Random random = new Random();
        StringBuilder txt = new StringBuilder(" elementy z listy to : ");
        if (a == 0) {
            throw new RuntimeException("a musi  byc rozne od zera");
        }
        for (int i = 1; i <= a; i++) {
            int x = random.nextInt(100);
            lista.add(x);
        }
        lista.sort(Comparator.reverseOrder());
        int min = a - 1;
        Integer suma = 0;
        Double sred = 0.0;
        for (Integer ele : lista
        ) {
            suma += ele + suma;
            sred = (double) Math.divideExact(suma,a);

        }

        txt.append(lista)
                .append("\nwartosc min :").append(lista.get(min))
                .append(" \t wartosc max: ").append(lista.get(0))
                .append(" \t srednia z liczb losowych: ").append(sred);
        // srednia nie liczy prawidlowo
        return txt.toString();
    }


    public String zadanie3b(Integer a) {
        List<Integer> lista = new ArrayList();

        Random random = new Random();
        StringBuilder txt = new StringBuilder(" elementy z listy to : ");
        Integer i= 1;
        do {
            int x = random.nextInt(100);
            lista.add(x);
            i++;
        }while (i<a);
        int min = a - 1;
        Integer suma = 0;
        Double sred = 0.0;
        for (Integer ele : lista
        ) {
            suma += ele + suma;
            sred = (double) Math.divideExact(suma,a);

        }

        txt.append(lista)
                .append("\nwartosc min :").append(lista.get(min))
                .append(" \t wartosc max  : ").append(lista.get(0))
                .append(" \t srednia z liczb losowych: ").append(sred);
        // srednia nie liczy prawidlowo
        return txt.toString();
    }
}
