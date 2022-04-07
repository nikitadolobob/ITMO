package lab5.models;

import lab5.enumerations.AdressField;

public class Address extends ReadableObject<Address, AdressField> implements Comparable<Address>{
    private String zipCode; //Поле может быть null
    private Location town; //Поле может быть null

    public Address(){
        super();
    }

    public void setZipCodeipCode(String zipCode) {this.zipCode = zipCode;}

    public void setTown(Location town) {this.town = town;}

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Location getTown() {
        return town;
    }

    @Override
    public String toString(){
        return String.format("почтовый индекс %s, город %s", getZipCode(), getTown());
    }

    @Override
    public int compareTo(Address o) {
        return getZipCode().compareTo(o.getZipCode());
    }
}
