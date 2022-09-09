package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.LiteryDTO;
import pl.cyber.trainess.demo.dto.OneStringRequest;
import pl.cyber.trainess.demo.dto.StringRequest;

import java.util.*;

@Service // aby prawidłowo korzystac z frameworka springa
public class BasicsService {
private static String SPACJA = " ";
    public String getSklejenie(final StringRequest request) {
    //    return request.getStringPierwszy()+request.getStringDrugi();
        // Alternatywna przy użyciu zewnetrznej bibloteki
        var string = new StringBuilder();
       // wersja prostsza : return request.getStringPierwszy()+ request.getStringDrugi()
        return string // za pomoca bibloteki
                .append(request.getStringPierwszy())
                .append(SPACJA)
                .append(request.getStringDrugi())
                .toString(); // toString generuje z wczesniejszych metod jednego wspolnego Stringa
    }
//Zadanie w oparciu o listy
    //region Zadanie w oparciu o kolekcje MAP
    public List<String> getWystapieniaLiterWZdaniu(final OneStringRequest request) {
        List<LiteryDTO> wystapienia= new ArrayList<>();
        Set<String> litery = new HashSet<>();
        List<String> wynik = new ArrayList<>();

        String zdanie = request.getValue();


        for (int i=0; i<zdanie.length(); i++){
            String litera=String.valueOf(zdanie.charAt(i));
            if (litera.matches("[a-zA-Z]+")){
                if(wystapienia.size()==0) {
                    litery.add(litera.toLowerCase());
//                    wystapienia.add(LiteryDTO.builder()
//                            .litera(litera.toLowerCase())
//                            .ilosc(1)
//                            .build());
                    // napisane przez konstruktor
                    wystapienia.add(new LiteryDTO(litera.toLowerCase(), 1));
                }else{
                    if (litery.contains(litera.toLowerCase())){
                        for(LiteryDTO element: wystapienia){
                            if (element.getLitera().equals(litera.toLowerCase())){
                                element.setIlosc(element.getIlosc() +1);
                            }
                        }
                    } else {
                        litery.add(litera.toLowerCase());
                        wystapienia.add(LiteryDTO.builder()
                                .litera(litera.toLowerCase())
                                        .ilosc(1)
                                        .build());
                    }
                }
            }
        }
        /*
        a - 4
        k - 1
        l - 1
        m - 1
        o - 1
        t - 1
                */
        for (LiteryDTO element: wystapienia){
            wynik.add(element.getLitera()+ "-" + element.getIlosc());
        }
        wynik. //zmienna okreslajaca liste Stringow jako wynik, ktory zostanie zwrocony do uzytkownika
                sort( // metod sort odpowiada za sortowanie zgodne z kluczem wskazanej listy (wynik)
                        String::compareTo); // klucz sortowania
        // lista zostanie posortowana alfabetycznie w sposob taki, ze
        //zostanie orownany element n z elementem n+1, a nastepnie zostanie zamieniony zgodnie z
        //wystapieniem w alfabecie
        return  wynik;

}
/*
{
Przyklad jak powinien wygladac jezyk Jason
    "stringPierwszy": "Ala ma ",
    "stringDrugi": "kota !!"

 */
    //zadanie w oparciu o listy
    //region Zadanie w oparciu o Mapy
    public List<String> getWystapieniaLiterWZdaniuMap(final OneStringRequest request){
        Map<String, Integer> wystapienia= new HashMap<>();
        List<String>wynik= new ArrayList<>();//lista naszych wynikow

        String zdanie= request.getValue().toLowerCase();

        for(int i=0;i<zdanie.length();i++){
            String litera= String.valueOf(zdanie.charAt(i));
            if (litera.matches("[a-zA-Z]")){
                if (wystapienia.containsKey(litera)) {
                    wystapienia.put(litera, wystapienia.get(litera) + 1);
                }else{
                    wystapienia.put(litera,1);
                }
                };
        }
        for (String element: wystapienia.keySet()){
            wynik.add(element+ " - "+ wystapienia.get(element));
        }
        return wynik;
    }


}