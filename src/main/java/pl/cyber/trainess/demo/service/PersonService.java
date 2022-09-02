package pl.cyber.trainess.demo.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.ImieDTO;
import pl.cyber.trainess.demo.dto.Person;
import pl.cyber.trainess.demo.dto.PersonDTO;
import pl.cyber.trainess.demo.dto.PersonRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mariusz Tański
 */

@Service
@RequiredArgsConstructor
public class PersonService {

  private List<Person> personList = new ArrayList<>();

  public void createPerson(final PersonRequest request) {
    //validator

    walidujOsobe(request);

    personList.add(Person.builder()
            .imie(request.getImie())
            .nazwisko(request.getNazwisko())
            .miasto(request.getMiasto())
            .dataUrodzenia(request.getDataUrodzenia())
            .plec(request.getPlec())
        .build());
  }

  private void walidujOsobe(final PersonRequest request) {
    boolean czyIstnieje = false;
    for (Person element : personList) {
      if(element.getImie().equals(request.getImie())
      && element.getNazwisko().equals(request.getNazwisko())
      && element.getDataUrodzenia().equals(request.getDataUrodzenia())
      ) {
        czyIstnieje = true;
      }
    }

    if(czyIstnieje) {
      throw new RuntimeException("Taka osoba już istnieje");
    }
  }

  public PersonDTO getPerson(final PersonRequest request) {
    for (Person element : personList) {
      if(element.getImie().equals(request.getImie())
          && element.getNazwisko().equals(request.getNazwisko())
          && element.getDataUrodzenia().equals(request.getDataUrodzenia())) {
        return PersonDTO.builder()
            .imie(element.getImie())
            .nazwisko(element.getNazwisko())
            .miasto(element.getMiasto())
            .dataUrodzenia(element.getDataUrodzenia())
            .plec(element.getPlec())
            .build();
      }
    }
    return PersonDTO.builder().build();
  }

  public PersonDTO getPersonParms(final String imie, final String nazwisko) {
    for (Person element : personList) {
      if(element.getImie().equals(imie) && element.getNazwisko().equals(nazwisko)) {
        return PersonDTO.builder()
            .imie(element.getImie())
            .nazwisko(element.getNazwisko())
            .miasto(element.getMiasto())
            .dataUrodzenia(element.getDataUrodzenia())
            .plec(element.getPlec())
            .build();
      }
    }
    return PersonDTO.builder().build();
  }

  public void updatePerson(final PersonRequest request) {
    for (Person element : personList) {
      if(element.getImie().equals(request.getImie())
          && element.getNazwisko().equals(request.getNazwisko())
          && element.getDataUrodzenia().equals(request.getDataUrodzenia())) {
        element.setMiasto(request.getMiasto());
      }
    }
  }
}
