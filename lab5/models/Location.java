package lab5.models;

import lab5.enumerations.LocationField;

public class Location extends ReadableObject<Location, LocationField> {
    private Float x; //Поле не может быть null
    private double y;
    private Double z; //Поле не может быть null
    private String name; //Поле может быть null

    public Location() {
        super();
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("Город: x = %s, y = %s, z = %s, название = %s", getX(), getY(), getZ(), getName());
    }

}
