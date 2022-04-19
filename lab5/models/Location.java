package lab5.models;

import lab5.enumerations.LocationField;

/**
 * Класс Локация
 */
public class Location extends ReadableObject<Location, LocationField> {
	/**
     * x-координата локации.
     */
    private Float x; //Поле не может быть null
	/**
     * y-координата локации.
     */
    private double y;
	/**
     * z-координата локации.
     */
    private Double z; //Поле не может быть null
	/**
     * название локации.
     */
    private String name; //Поле может быть null
	
	
	/**
     * Метод, создающий новую локацию
     */
    public Location() {
        super();
    }
	/**
     * Устанавливает x-координату локации
     *
     * @param x x-координата локации
     */
    public void setX(Float x) {
        this.x = x;
    }
	/**
     * Устанавливает y-координату локации
     *
     * @param y y-координата локации
     */
    public void setY(double y) {
        this.y = y;
    }
	/**
     * Возвращает x-координату локации
     *
     * @return x-координата локации
     */
    public Float getX() {
        return x;
    }
	/**
     * Возвращает y-координату локации
     *
     * @return y-координата локации
     */
    public double getY() {
        return y;
    }
	/**
     * Возвращает z-координату локации
     *
     * @return z-координата локации
     */
    public Double getZ() {
        return z;
    }
	
	/**
     * Устанавливает z-координату локации
     *
     * @param z z-координата локации
     */
    public void setZ(Double z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }
	/**
     * Устанавливает название локации
     *
     * @param name название локации
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
     * Выводит строковое представление локации.
     *
     * @return строка
     */
    @Override
    public String toString(){
        return String.format("Город: x = %s, y = %s, z = %s, название = %s", getX(), getY(), getZ(), getName());
    }

}
