package pl.cyber.trainess.exam.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class Zliczanie {
    private  final String value;

    @ConstructorProperties({"value"})
    public Zliczanie(final String value) {
        if (value ==null || value.length()==0){
            throw new RuntimeException("brak zdania");
        }
        this.value = value;
    }
}
