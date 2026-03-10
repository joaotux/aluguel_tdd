package br.com.umdesenvolvedor.model;

public class Address {

    private Long id;
 
    private String city;
 
    private String region;
 
    private String street;
 
    private String neighborhood;
 
    private String zipcode;

    public Address(String city, String region, String street, String neighborhood, String zipcode) {
        this.city = city;
        this.region = region;
        this.street = street;
        this.neighborhood = neighborhood;
        this.zipcode = zipcode;
    }

}
