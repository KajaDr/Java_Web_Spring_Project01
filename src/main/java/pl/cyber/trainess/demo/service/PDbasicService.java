package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.ListaRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class PDbasicService {
    static List<Integer>liczby= new ArrayList<>();
    public static Integer sumaLiczb(final Integer a, final Integer b) {
        Integer n = b-a;
        Integer suma=0;
        for (n=0; a+n<=b; n++){
            liczby.add(a+n);
            suma=+(a+n)+suma;
        }
        System.out.println(liczby);
        return suma;
    }

    public static Integer getZadanie8(final ListaRequest request) {
    }
}
