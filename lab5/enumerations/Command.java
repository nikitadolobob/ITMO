package lab5.enumerations;


import lab5.Main;
import lab5.UserInteraction;
import lab5.models.Address;
import lab5.models.Organization;
import lab5.models.ReadableObject;
import lab5.parsers.YamlWriter;

import java.util.*;

/**
 * Список команд
 */
public enum Command {

    /**
     * Справка по доступным командам.
     */
    HELP("help", " : справка по доступным командам") {
        @Override
        public void func(Hashtable<Integer, Organization> o, String... args) {
            System.out.println("Выведите одну из следующих команд:");
            for (Command command : Command.values()) {
                System.out.println(command);
            }
        }
    },
    /**
     * вывести информация о коллекции.
     */
    INFO("info", " : вывести информация о коллекции") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            System.out.println("Информация о коллекции:");
            System.out.printf("Тип коллекции %s\n", organizations.getClass().getTypeName());
            System.out.printf("Размер коллекции %s\n", organizations.size());
        }
    },
    /**
     * вывести все элементы коллекции.
     */
    SHOW("show", " : вывести все элементы коллекции") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            System.out.println("В коллекции содержатся следующие элементы");
            for (Organization organization : organizations.values()) {
                System.out.println(organization);
            }
        }
    },
    /**
     * добавить новый элемент с заданным ключом.
     */
    INSERT("insert", " null {element} : добавить новый элемент с заданным ключом") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            int key = getIntegerValue(args);
            if(key == Integer.MAX_VALUE) return;
            Organization organization = new Organization();
            organization.readElement(OrganizationField.values());
            organizations.put(key, organization);
            System.out.printf("В коллекцию добавлен элемент %s", organization);
        }
    },
    /**
     * обновить значение элемента коллекции, id которого равен заданному.
     */
    UPDATE("update", " id {element} : обновить значение элемента коллекции, id которого равен заданному") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            int id = getIntegerValue(args);
            if(id == Integer.MAX_VALUE) return;

            int oldKey = 0;
            Organization oldOrganization = null;

            for(Integer key: organizations.keySet()){
                Organization organization = organizations.get(key);
                if (organization.getId() == id){
                    oldOrganization = organization;
                    oldKey = key;
                    break;
                }
            }

            if( oldOrganization== null){
                System.out.printf("Организации с id = %s нет в коллекции!", id);
                return;
            }
            organizations.remove(oldKey);
            oldOrganization.readElement(OrganizationField.values());
            organizations.put(oldKey, oldOrganization);
            System.out.printf("Вы обновили организацию с id = %s\n", id);
        }
    },
    /**
     * удалить элемент из коллекции по его ключу.
     */
    REMOVE_KEY("remove_key", " null : удалить элемент из коллекции по его ключу") {
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            Integer key = getIntegerValue(args);
            organizations.remove(key);
            System.out.printf("Элемент с ключем key = %s удален", key);

        }
    },
    /**
     * очистить коллекцию.
     */
    CLEAR("clear", " : очистить коллекцию") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            organizations.clear();
            System.out.println("Коллекция очищена");
        }
    },
    /**
     * сохранить коллекцию в файл.
     */
    SAVE("save", " : сохранить коллекцию в файл") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            YamlWriter writer = new YamlWriter(organizations, "C:\\Users\\myav\\IdeaProjects\\ITMO_labs\\src\\lab5\\resources\\output.json");
            writer.writeData();
            System.out.println("Коллекция сохранена в файл");

        }
    },
    /**
     * считать и исполнить скрипт из указанного файла.
     */
    EXECUTE_SCRIPT("execute_script", " fileName : считать и исполнить скрипт из указанного файла") {
        @Override
        public void func(Hashtable<Integer,Organization> organizations, String... args) {
            String filename = null;
            for (String arg : args) {
                filename = arg;
                break;
            }

            if (filename != null) System.out.println("имя файла " + filename);
            try {
                UserInteraction scriptReader = new UserInteraction(organizations, filename);

                System.out.println(scriptReader);
                scriptReader.start();

            } catch (Exception e) {
                System.out.println("Пустое или некорректное имя файла!");
            }
            ReadableObject.setInputFileName(null);
        }
    },
    /**
     * завершить программу без сохранения в файл.
     */
    EXIT("exit", "завершить программу без сохранения в файл") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String ... args) {
            System.out.println("Выход без сохранения изменений");
            System.exit(0);
        }
    },
    /**
     * удалить из коллекции все элементы, меньшие, чем заданный.
     */
    REMOVE_LOWER("remove_lower", " {element} : удалить из коллекции все элементы, меньшие, чем заданный") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String... args) {
            Organization newOrganization = new Organization();
            newOrganization.readElement(OrganizationField.values());

            for(Integer key: organizations.keySet()){
               Organization organization = organizations.get(key);
               if(organization.compareTo(newOrganization) < 0) organizations.remove(key);
            }
            System.out.println("Из коллекции удалены все элементы, меньшие следующего:");
            System.out.println(newOrganization);
        }
    },
    /**
     * заменить значение по ключу, если новое значение больше старого.
     */
    REPLACE_IF_GREATER("replace_if_greater", " null {element} : заменить значение по ключу, если новое значение больше старого") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String... args) {
            int keyValue = getIntegerValue(args);
            if(keyValue == Integer.MAX_VALUE) return;

            Organization newOrganization = new Organization();
            newOrganization.readElement(OrganizationField.values());
            Organization oldOrganization = organizations.get(keyValue);
            if(oldOrganization.compareTo(newOrganization) < 0){
                organizations.remove(keyValue);
                organizations.put(keyValue, newOrganization);
                System.out.println("Значение элемента было обновлено.");
            }
            else System.out.println("Значение элемента не было обновлено, так как новое значение не превышает старое.");
        }
    },
    /**
     * удалить из коллекции все элементы, ключ которых превышает заданный.
     */
    REMOVE_GREATER_KEY("remove_greater_key", " null : удалить из коллекции все элементы, ключ которых превышает заданный") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String... args) {
            Integer keyValue = getIntegerValue(args);
            ArrayList<Integer>delete = new ArrayList<>();
            if(keyValue == Integer.MAX_VALUE) return;
            for(Integer key: organizations.keySet()){
                if(key > keyValue) delete.add(key);
            }
            for(int key : delete){
                organizations.remove(key);
            }
            System.out.printf("Из коллекции удалены организации с id > %s\n", keyValue);
        }
    },
    /**
     * вывести элементы, значение поля fullName которых больше заданного.
     */
    FILTER_GREATER_THAN_FULL_NAME("filter_greater_than_full_name", " fullName: вывести элементы, значение поля fullName которых больше заданного") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String... args) {
            if(args.length == 0){
                System.out.println("Вы не указали значение fullName");
                return;
            }
            System.out.printf("Организации, значение поля fullName которых больше %s:\n", args[0]);
            for (Organization organization : organizations.values()){
                String fullName = organization.getFullName();
                if(fullName.compareTo(args[0]) > 0) System.out.println(organization);
            }
        }
    },
    /**
     * вывести значения поля fullName всех элементов в порядке возрастания.
     */
    PRINT_FIELD_ASCENDING_FULL_NAME("print_field_ascending_full_name", ": вывести значения поля fullName всех элементов в порядке возрастания") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String... args) {
            ArrayList<String> names = new ArrayList<>();
            for(Organization organization : organizations.values()){
                String name = organization.getFullName();
                names.add(name);
            }
            Collections.sort(names);
            System.out.println("Значения полей fullName в порядке возрастания:");
            for (String name : names){
                System.out.println(name);
            }
        }
    },
    /**
     * вывести значения поля postalAddress всех элементов в порядке убывания.
     */
    PRINT_FIELD_DESCENDING_POSTAL_ADDRESS("print_field_descending_postal_address", ": вывести значения поля postalAddress всех элементов в порядке убывания\n") {
        @Override
        public void func(Hashtable<Integer, Organization> organizations, String... args) {
            ArrayList<Address> adresses = new ArrayList<>();
            for(Organization organization : organizations.values()){
                Address address = organization.getPostalAddress();
                adresses.add(address);
            }
            adresses.sort(Collections.reverseOrder());
            System.out.println("Значения полей postalAdress в порядке убывания:");
            for(Address address : adresses){
                System.out.println(address);
            }

        }
    };


    /**
     * Имя команды.
     */
    public String name, /**
     * Пояснение к команде.
     */
    explanation;
    /**
     * Reader для чтения данных.
     */
    Scanner reader;

    /**
     * Конструктор, создает новую команду
     *
     * @param name        имя команды
     * @param explanation пояснение к ней
     */
    Command(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
        this.reader = new Scanner(System.in);
    }

    /**
     * Метод, который должен выполниться при выполнении команды.
     *
     * @param o    коллекция, с которой необходимо работать
     * @param args аргументы команды
     */
    public abstract void func(Hashtable<Integer, Organization> o, String... args);

    /**
     * Метод, обрабатывающий правильность введенного значение ключа, когда он есть у команды в аргументах (insert key и тд)
     *
     * @param args значение, которое необходимо обработать
     * @return целочисленное значение
     */
    protected int getIntegerValue(String ... args){
        if(args.length == 0){
            System.out.println("Вы не ввели значение ключа!");
            return Integer.MAX_VALUE;
        }
        int keyValue;
        try{
            keyValue = Integer.parseInt(args[0]);
            return keyValue;
        }catch (Exception e){
            System.out.println("Вы ввели некорректное значение ключа!");
            return Integer.MAX_VALUE;
        }
    }


    @Override
    public String toString() {
        return this.name  + this.explanation;
    }

}
