package lab5.models;

import lab5.enumerations.CoordinatesField;

/**
 * Координаты организации
 */
public class Coordinates extends ReadableObject<Coordinates, CoordinatesField> {
	/**
     * x-координата
     */
    private double x; //Максимальное значение поля: 280
	/**
     * y-координата
     */
    private float y;

	 /**
     * Устанавливает x.
     *
     * @param x x-координата
     */
    public void setX(double x){this.x = x;}
	/**
     * Устанавливает y.
     *
     * @param y y-координата
     */
    public void setY(float y){this.y = y;}
	/**
     * Возвращает значение x.
     *
     * @return x-координата
     */
    public double getX() {
        return x;
    }
	/**
     * Возвращает значение y.
     *
     * @return y-координата
     */
    public float getY() {
        return y;
    }
	/**
     * Возвращает строковое представление координат.
     *
     * @return строка
     */
    @Override
    public String toString(){
        return String.format("Координаты: x = %s, y = %s", getX(), getY());
    }
	/**
     * Возвращает хэш-код координат.
     *
     * @return целое число
     */
    @Override
    public int hashCode(){
        return (int) (getX() + getY() * 37);
    }
}