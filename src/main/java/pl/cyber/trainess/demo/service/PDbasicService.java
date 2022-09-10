package pl.cyber.trainess.demo.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.ListaRequest;
import pl.cyber.trainess.demo.dto.RozwiazanieRownaniaRequest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Service
public class PDbasicService {

    static List<Integer>liczby= new ArrayList<>();

    public  Integer sumaLiczb(final Integer a, final Integer b) {
        Integer n = b-a;
        Integer suma=0;
       if (a>b) {
           throw new RuntimeException("Wartosc parametru b powinna byc mniejsza niz wartosci b");
       }
        for (n=0; a+n<=b; n++){
            liczby.add(a+n);
            suma=+(a+n)+suma;
        }
        System.out.println(liczby);
        return suma;
    }

    public String getZadanie8(final ListaRequest request) {
        List<Integer> negativeList = new ArrayList<>();
        int suma = 0;
        for (int x : request.getIntList()
        ) {
            if (x < 0) {
                negativeList.add(x);
            } else {
                suma = x + suma;
            }

        }
        System.out.println(negativeList);
        System.out.println(suma);
        return negativeList + "oraz suma liczb dodatnich wynosi: " + suma;
    }
    public String getZadanie9(final Integer a, final Integer b, final Integer c) {
        System.out.println(a + "^2x" + b + "x" + c);
        System.out.println("wiec liczymy delte: ");
        Double delta = Double.valueOf((Math.pow(b, 2)) - (4 * a * c));
        Double piDelta= Math.sqrt(delta);
        System.out.println(delta + " Pidelta :" +piDelta);

        Double x1 = 0.0;
        Double x2 = 0.0;
       Double x0 = 0.0;
        if (a==0){
            return "To nie jest rownanie kwadratowe";
        }
        if (delta > 0) {
            x1 =Double.valueOf( -((b) + piDelta) / 2 * a);
            x2 = Double.valueOf((-b) + piDelta / 2 * a);
           return "x1 :" +x1 + " " + " x2: "+x2;
        } else if (delta == 0) {
            x0 =Double.valueOf( (-(b / (2 * a))));
            return "Wartosc x0 jest równa: " +x0;
        } else {
           return "brak wyniku";
        }
    }
public String rozwiazanieRownania(final RozwiazanieRownaniaRequest request) {
    Double delta = 0.0;
    Double x1 = 0.0;
    Double x2 = 0.0;

    Double a = request.getA();
    Double b = request.getB();
    Double c = request.getC();

    delta = Math.pow(b, 2) - (4 * a * c);
    if (delta == 0) {
        x1 = -b / 2 * a;
        return "Jeden pierwiastek. Wynik: " + x1;
    } else if (delta < 0) {
        return "brak pierwiastkow";
    } else{
        x1= (-b-Math.sqrt(delta))/ (2*a);
        x2= (-b+Math.sqrt(delta))/ (2*a);
        return "Dwa pierwiastki: x1 : "+x1+ "x2: " +x2;

}

        }

    public String rozwiazanieRownaniaZaokraglenia(final RozwiazanieRownaniaRequest request) {
        Locale englishLocale = Locale.ENGLISH;
        Locale polishLocale = Locale.forLanguageTag("pl-PL");

        Locale.setDefault(polishLocale);
        DecimalFormat df = new DecimalFormat("#,###.00"); // okreslanie polskiej notacji

        // do zaokraglania liczb do wartosci setnej
        //"#,###.00" -> ile zer po przecinku
        /*
        1.10
        "#,###.00">1,10

        1101.10
        "#,###.00">1 101,10

        1.10
        "#,###.0#">1,10
         1.1
        "#,###.0#">1,1

         1.113142115
        "#,###.0#">1,11  <- zaokragla do 100 wartosci

        1111.10
        "#,#"> 1111.1

         */

        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;

        Double a = request.getA();
        Double b = request.getB();
        Double c = request.getC();

        delta = Math.pow(b, 2) - (4 * a * c);
        if (delta == 0) {
            x1 = -b / 2 * a;
            return "Jeden pierwiastek. Wynik: " + df.format(x1);
        } else if (delta < 0) {
            return "brak pierwiastkow";
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return "Dwa pierwiastki: x1 : " + df.format(x1) + "x2: " + df.format(x2);
        }
    }
}

