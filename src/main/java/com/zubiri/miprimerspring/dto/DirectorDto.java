package com.zubiri.miprimerspring.dto;

public class DirectorDto {

    private String name;
    private int sueldo_anual;

    public DirectorDto(String name, int sueldo_anual) {
        this.name = name;
        this.sueldo_anual = sueldo_anual;
    }

    public String getName() {
        return name;
    }

    public int getSueldo_anual() {
        return sueldo_anual;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSueldo_anual(int sueldo_anual) {
        this.sueldo_anual = sueldo_anual;
    }

    @Override
    public String toString() {
        return "DirectorDto{" + "name=" + name + ", sueldo_anual=" + sueldo_anual + '}';
    }
    
}
