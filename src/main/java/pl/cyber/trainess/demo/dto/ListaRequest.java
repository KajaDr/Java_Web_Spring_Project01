package pl.cyber.trainess.demo.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
public class ListaRequest {
    private final List<Integer> intList;
@ConstructorProperties({"intList"})
    public ListaRequest(final List<Integer> intList) {
        this.intList = intList;
    }

}
