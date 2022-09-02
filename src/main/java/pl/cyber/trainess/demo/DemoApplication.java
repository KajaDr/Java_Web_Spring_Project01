package pl.cyber.trainess.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mariusz Tański
 */

@SpringBootApplication // adnotacja, której zadaniem jest uruchomienie aplikacji Springowej
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
