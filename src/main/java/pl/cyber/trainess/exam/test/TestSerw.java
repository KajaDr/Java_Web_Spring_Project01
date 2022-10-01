package pl.cyber.trainess.exam.test;

import pl.cyber.trainess.exam.dto.Liter2DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestSerw {

    public String zadanietest() {
        List<Liter2DTO> literyKtorewystepuja = new ArrayList<>();
        Set<String> literki = new HashSet<>();
        List<String> wynik = new ArrayList<>();

        String zdanie = "Ala ma kota";

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


        return wynik + zdanie;
    }
}
