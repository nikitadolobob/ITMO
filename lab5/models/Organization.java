package lab5.models;

import lab5.enumerations.OrganizationField;
import lab5.enumerations.OrganizationType;

import java.time.ZonedDateTime;

public class Organization extends ReadableObject<Organization, OrganizationField> implements Comparable<Organization> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле не может быть null
    private int employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null


    public Organization(){
        super();
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return  this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {this.coordinates = coordinates;}

    public void setCreationDate(java.time.ZonedDateTime creationDate) {this.creationDate = creationDate;}

    public void setAnnualTurnover(Long annualTurnover) {this.annualTurnover = annualTurnover;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public void setEmployeesCount (int employeesCount) {this.employeesCount = employeesCount;}

    public void setType(OrganizationType type) {this.type = type;}

    public void setPostalAddress(Address postalAddress) {this.postalAddress = postalAddress;}

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }


    @Override
    public String toString(){
        return String.format("Это организация %s с номером %s .Координаты: %s, \n" +
                "дата создания %s, годовой оборот %s, полное имя %s,\n количество сотрудников %s, " +
                "тип организации %s, адрес %s", getName(), getId(), getCoordinates(), getCreationDate(),
                getAnnualTurnover(), getFullName(), getEmployeesCount(), getType(), getPostalAddress());
    }


    @Override
    public int compareTo(Organization o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public int hashCode(){ //нужно для сортировки при добавлении в коллкцию!
        return this.getId();
    }
}
