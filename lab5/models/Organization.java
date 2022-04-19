package lab5.models;

import lab5.enumerations.OrganizationField;
import lab5.enumerations.OrganizationType;

import java.time.ZonedDateTime;

/**
 * The type Organization.
 */
public class Organization extends ReadableObject<Organization, OrganizationField> implements Comparable<Organization> {
    /**
     * The Id.
     */
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * The Name.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * The Coordinates.
     */
    private Coordinates coordinates; //Поле не может быть null
    /**
     * The Creation date.
     */
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * The Annual turnover.
     */
    private Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    /**
     * The Full name.
     */
    private String fullName; //Поле не может быть null
    /**
     * The Employees count.
     */
    private int employeesCount; //Значение поля должно быть больше 0
    /**
     * The Type.
     */
    private OrganizationType type; //Поле не может быть null
    /**
     * The Postal address.
     */
    private Address postalAddress; //Поле не может быть null


    /**
     * Instantiates a new Organization.
     */
    public Organization(){
        super();
    }

    /**
     * Копирует другую организацию.
     *
     * @param o организация
     */
    public Organization(Organization o){
        this.id = o.id;
        this.annualTurnover = o.annualTurnover;
        this.coordinates = o.coordinates;
        this.creationDate = o.creationDate;
        this.name = o.name;
        this.employeesCount = o.employeesCount;
        this.fullName = o.fullName;
        this.postalAddress = o.postalAddress;
        this.type = o.type;
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id integer.
     *
     * @return the integer
     */
    public Integer getId(){
        return  this.id;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {this.coordinates = coordinates;}

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(java.time.ZonedDateTime creationDate) {this.creationDate = creationDate;}

    /**
     * Sets annual turnover.
     *
     * @param annualTurnover the annual turnover
     */
    public void setAnnualTurnover(Long annualTurnover) {this.annualTurnover = annualTurnover;}

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
    public void setFullName(String fullName) {this.fullName = fullName;}

    /**
     * Sets employees count.
     *
     * @param employeesCount the employees count
     */
    public void setEmployeesCount (int employeesCount) {this.employeesCount = employeesCount;}

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(OrganizationType type) {this.type = type;}

    /**
     * Sets postal address.
     *
     * @param postalAddress the postal address
     */
    public void setPostalAddress(Address postalAddress) {this.postalAddress = postalAddress;}

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Gets annual turnover.
     *
     * @return the annual turnover
     */
    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets employees count.
     *
     * @return the employees count
     */
    public int getEmployeesCount() {
        return employeesCount;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * Gets postal address.
     *
     * @return the postal address
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

	/**
     * Возвращает строковое представление организации.
     *
     * @return строка
     */
    @Override
    public String toString(){
        return String.format("Это организация %s с номером %s. Координаты: %s,\n" +
                "дата создания %s, годовой оборот %s, полное имя %s,\nколичество сотрудников %s, " +
                "тип организации %s, адрес %s\n", getName(), getId(), getCoordinates(), getCreationDate(),
                getAnnualTurnover(), getFullName(), getEmployeesCount(), getType(), getPostalAddress());
    }
	
	/**
     * Возвращает результат сравнения двух организаций
     *
     * @return результат сравнения ID организаций
     */
    @Override
    public int compareTo(Organization o) {
        return this.getId().compareTo(o.getId());
    }
	/**
     * Возвращает хэшкод организации
     *
     * @return ID организации
     */
    @Override
    public int hashCode(){ //нужно для сортировки при добавлении в коллкцию!
        return this.getId();
    }
}
