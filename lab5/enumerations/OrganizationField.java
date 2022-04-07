package lab5.enumerations;

import lab5.Field;
import lab5.models.Address;
import lab5.models.Coordinates;
import lab5.models.Organization;

import java.time.ZonedDateTime;

import static java.lang.Math.abs;

public enum OrganizationField implements Field<Organization> {
    NAME(false) {
        @Override
        public String get(Organization organization) {
            return organization.getName();
        }

        @Override
        public void set(Organization organization, String... args) throws Exception {
            if (args.length == 0) throw new Exception("Название организации не может быть пустым!");
            organization.setName(args[0]);
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите название организации. Оно не может быть пустым!");
        }
    },
    COORDINATES(false) {
        @Override
        public Coordinates get(Organization organization) {
            return organization.getCoordinates();
        }

        @Override
        public void set(Organization organization, String... args) {
            Coordinates coordinates = new Coordinates();
            coordinates.readElement(CoordinatesField.values()); //!!
            organization.setCoordinates(coordinates);
        }

        @Override
        public void read(Organization organization) {
            writeInputMessage();
            set(organization);
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите координаты позиции, в которой находится организация.");
        }
    },
    CREATIONDATE(false) {
        @Override
        public ZonedDateTime get(Organization organization) {
            return organization.getCreationDate();
        }

        @Override
        public void set(Organization organization, String... args) {
            if (args.length == 0) {
                if (organization.getCreationDate() != null) return; //обновление организации
                organization.setCreationDate(ZonedDateTime.now());
            } else {
                for (String value : args) {
                    try {
                        ZonedDateTime zonedDateTime = ZonedDateTime.parse(value);
                        organization.setCreationDate(zonedDateTime);
                    } catch (Exception e) {
                        System.out.println("Некорректная дата!");
                    }
                    break;
                }
            }


        }

        @Override
        public void read(Organization organization) {
            set(organization);
        }
    },
    ANNUALTURNOVER(false){
        @Override
        public Long get(Organization object) {
            return object.getAnnualTurnover();
        }

        @Override
        public void set(Organization object, String... args) throws Exception {
            object.setAnnualTurnover(Long.parseLong(args[0]));
        }

        @Override
        public void writeInputMessage(){
            System.out.println("Введите ежегодный оборот организации.");
        }
    },
    FULLNAME(false){
        @Override
        public String get(Organization object) {
            return object.getFullName();
        }

        @Override
        public void set(Organization object, String... args) throws Exception {
            object.setFullName(args[0]);
        }

        @Override
        public void writeInputMessage(){
            System.out.println("Введите полное название организации");
        }
    },
    EMPLOYEESCOUNT(false){
        @Override
        public Integer get(Organization object) {
            return object.getEmployeesCount();
        }

        @Override
        public void set(Organization object, String... args) throws Exception {
            int count = Integer.parseInt(args[0]);
            if(count <= 0) throw new Exception("Значение должно быть больше 0");
            object.setEmployeesCount(count);
        }

        @Override
        public void writeInputMessage(){
            System.out.println("Введите количество сотрудников организации.");
        }
    },
    TYPE(false){
        @Override
        public OrganizationType get(Organization object) {
            return object.getType();
        }

        @Override
        public void set(Organization object, String... args) throws Exception {
            if(args.length == 0) throw new Exception("Тип организации не может быть null!");
            for(OrganizationType type : OrganizationType.values()){
                if(type.name().equals(args[0])){
                    object.setType(type);
                    return;
                }
            }
            throw new Exception("Такого типа организации нет!");
        }
        @Override
        public void writeInputMessage(){
            System.out.println("Введите тип организации. Можно ввести одно из следующих значений");
            for(OrganizationType type : OrganizationType.values()){
                System.out.println(type.name());
            }
        }
    },
    ADRESS(false){
        @Override
        public Address get(Organization object) {
            return object.getPostalAddress();
        }

        @Override
        public void set(Organization object, String... args) throws Exception {
            Address address = new Address();
            address.readElement(AdressField.values());
            object.setPostalAddress(address);
        }

        @Override
        public void read(Organization organization){
            writeInputMessage();
            try {
                set(organization);
            } catch (Exception e) {
                System.out.println("Некорректный адрес.");
            }
        }

        @Override
        public void writeInputMessage(){
            System.out.println("Введите почтовый адрес организации.");
        }
    },
    ID( false) {
        @Override
        public Integer get(Organization organization) {
            return organization.getId();
        }

        private void generateID(Organization organization){
            if(organization.getId() != null) return; //id уже сгенерировано, обновляем эл-т
            int power = 1, id = 0;
            for (OrganizationField field : OrganizationField.values()) {
                if(field.name().equals("ID") || field.get(organization) == null) continue;
                id += power * field.get(organization).hashCode();
                power *= 29;
            }
            organization.setId(abs(id));
        }

        @Override
        public void set(Organization organization, String... args) throws Exception {
            if (args.length == 0) generateID(organization);
            else {
                int id = Integer.parseInt(args[0]);
                if (id <= 0) throw new Exception("id организации должно быть больше 0");
                organization.setId(Integer.parseInt(args[0]));
            }
        }

        @Override
        public void read(Organization organization) {
            try {
                set(organization);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    };

    protected final boolean nullValid;


    OrganizationField(boolean nullValid) {
        this.nullValid = nullValid;
    }

    @Override
    public boolean getNullValid() {
        return nullValid;
    }

    @Override
    public void read(Organization organization) {
        readField(this::set, organization, getNullValid());
    }

    @Override
    public void writeInputMessage() {

    }




}
