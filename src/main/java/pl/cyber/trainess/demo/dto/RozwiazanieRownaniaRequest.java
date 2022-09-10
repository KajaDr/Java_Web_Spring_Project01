package pl.cyber.trainess.demo.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class RozwiazanieRownaniaRequest {
    private  final Double a;
    private  final Double b;
    private  final Double c;
@ConstructorProperties({"a","b","c"})
    public RozwiazanieRownaniaRequest(final Double a, final Double b, final Double c) {
    if (a ==0){
        throw  new RuntimeException("Parametr a jest rowny 0");
    }
    this.a = a;
        this.b = b;
        this.c = c;
    }
}
