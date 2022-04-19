package lab5.models;

import lab5.enumerations.AdressField;

/**
 * Адрес организации
 */
public class Address extends ReadableObject<Address, AdressField> implements Comparable<Address>{
	/**
     * Почтовый код организации
     */
    private String zipCode; //Поле может быть null
	/**
     * Город, в котором находится организация
     */
    private Location town; //Поле может быть null

	/**
     * Конструктор, создающий новый адрес
     */
    public Address(){
        super();
    }

	/**
     * Метод, устанавливающий значение почтового индекса
     *
     * @param zipCode почтовый индекс
     */
    public void setZipCodeipCode(String zipCode) {this.zipCode = zipCode;}

	/**
     * Метод, устанавливающий город, в котором находится организация
     *
     * @param town город
     */
    public void setTown(Location town) {this.town = town;}
	/**
     * Метод, возвращающий почтовый индекс
     *
     * @return почтовый индекс
     */
    public String getZipCode() {
        return zipCode;
    }
	
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
	/**
     * Метод, возвращающий город организации
     *
     * @return город
     */
    public Location getTown() {
        return town;
    }

	/**
     * Выводит строковое представление адреса
     *
     * @return строка с описанием адреса
     */
    @Override
    public String toString(){
        return String.format("почтовый индекс %s, город %s", getZipCode(), getTown());
    }
	/**
     * Сравнивает два адреса между собой
     *
     * @return результат сравнения почтовых индексов организаций
     */
    @Override
    public int compareTo(Address o) {
        return getZipCode().compareTo(o.getZipCode());
    }
}
