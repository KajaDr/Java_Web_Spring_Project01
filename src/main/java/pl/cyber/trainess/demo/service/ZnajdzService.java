package pl.cyber.trainess.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
public class ZnajdzService {
    // uzycie funkcji  randomowej
    private Integer losowaLiczba = 0;
    Random r = new Random();

    public ZnajdzService() {
        this.losowaLiczba = r.nextInt(990) + 10;

        log.info("Wylosowana liczba to :" + this.losowaLiczba.toString());
    }

    public String getLiczba(final Integer a) {
        if (Objects.equals(a,losowaLiczba)) {
            return "Zgadłes!";
        } else if (a < losowaLiczba) {
            return"wygenerowana liczba jest mniejsza";
        } else {
            return "Wygenerowana liczba jest wieksza";
        }
    }
}
