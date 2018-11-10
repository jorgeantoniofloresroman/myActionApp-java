package com.jflores.visualApp.data;


public class Item {

    private String shortDescription;
    private String details;

    //Constructor de la clase
    public Item(String shortDescription, String details) {
        this.shortDescription = shortDescription;
        this.details = details;
    }

//Getters y Setters

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    //Sobreescribe el método toString para que si imprimimos un Item devuelva la descripción.
    @Override
    public String toString() {
        return shortDescription;
    }
}
