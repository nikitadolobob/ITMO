package lab5.models;

import lab5.enumerations.CoordinatesField;

public class Coordinates extends ReadableObject<Coordinates, CoordinatesField> {
    private double x; //Максимальное значение поля: 280
    private float y;


    public void setX(double x){this.x = x;}

    public void setY(float y){this.y = y;}

    public double getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString(){
        return String.format("Координаты: x = %s, y = %s", getX(), getY());
    }

    @Override
    public int hashCode(){
        return (int) (getX() + getY() * 37);
    }
}