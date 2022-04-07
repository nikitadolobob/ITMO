package lab5.parsers;

import lab5.models.Address;
import lab5.models.Coordinates;
import lab5.models.Location;
import lab5.models.Organization;
import lab5.enumerations.AdressField;
import lab5.enumerations.CoordinatesField;
import lab5.enumerations.LocationField;
import lab5.enumerations.OrganizationField;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class YamlReader extends YamlParser {

    private final ArrayList<String> lines;
    private  InputStreamReader inputStreamReader;

    private Organization organization;
    private Coordinates coordinates;
    private Address address;
    private Location town;

    private int number = 0;

    public YamlReader(Hashtable<Integer, Organization> o, String filePath){

        super(o, filePath);
        this.lines = new ArrayList<>();
        this.inputStreamReader = null;
        organization = new Organization();
        coordinates = new Coordinates();
        address = new Address();
        town = new Location();

    }


    @Override
    protected void openFile(){
        //Всю обработку исключений мне предложила сделать ide - наверное это правильно

        InputStream inputStream = null;
        //пробуем считать файл в объект InputStream, если файла с таким адресом нет - бросаем исключение
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Теперь InputStreamReader-y присваиваем объект InputStream, из которого он будет считывать данные
        inputStreamReader = null;
        if (inputStream != null) {
            inputStreamReader = new InputStreamReader(inputStream);
        }
    }


    public void readData(){

        openFile();
        //считываем теперь данные из файла в первый раз
        int data = 0;
        try {
            //в прошлый раз в inputStreamReader могло ничего не присвоиться и он остался null - тогда неоткуда считывать
            if (inputStreamReader != null) {
                data = inputStreamReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //последовательно выводим данные
        StringBuilder line = new StringBuilder();
        while(data != -1){
            char theChar = (char) data;
            try {
                if (inputStreamReader != null) {
                    data = inputStreamReader.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(theChar == '\n') {
                lines.add(line.toString());
                line = new StringBuilder();
            }
            else line.append(theChar);
        }

        //обработка строк
        parseYAML();

        //видимо, закрываем поток вывода
        try {
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseYAML(){
        boolean objectsStarted = false;
        boolean newObject = false;

        String key ="";
        String value = "";
        ArrayList<String> parentObject = new ArrayList<>();

        for(String line : this.lines) {

            if(line.contains("[")) {
                objectsStarted = true;
                parentObject.add("ORGANIZATIONS");
            }
            if(!objectsStarted) continue;

            if(line.contains("{")) {
                newObject = true;
                if(parentObject.isEmpty()) {
                    parentObject.add("ORGANIZATIONS");
                    add();
                }
            }
            if(newObject && line.contains(":")){
                key = line.substring(0, line.indexOf(":"));
                value = line.substring(line.indexOf(":"));

                key = key.substring(key.indexOf("\"") + 1, key.lastIndexOf("\""));
                if(value.contains("\"")){
                    value = value.substring(value.indexOf("\"") + 1, value.lastIndexOf("\""));
                    try {
                        fillOrganization(key, value, parentObject.get(parentObject.size() - 1));
                    }catch (Exception e){
                        System.out.println("Некорректные значения во входном файле " + e.getMessage());
                        System.exit(0);
                    }

                }
                else if(value.contains("{")) parentObject.add(key);

            }
            if(line.contains("}")) {
                parentObject.remove(parentObject.size() - 1);
            }

            if(line.contains("]")) {
                objectsStarted = false;
                add();
            }
        }

    }

    private void add(){
        organization.setCoordinates(coordinates);
        address.setTown(town);
        organization.setPostalAddress(address);
        organizations.put(number++, organization);
    }

    public void fillOrganization(String key, String value, String parent) throws Exception {
        //System.out.println(key + " " + value + " " + parent);
        switch (parent) {
            case "TOWN":
                town.setFields(key, value, LocationField.values());
                break;
            case "POSTALADDRESS":
                address.setFields(key, value, AdressField.values());
                break;
            case "COORDINATES":
                coordinates.setFields(key, value, CoordinatesField.values());
                break;
            case "ORGANIZATIONS":
                organization.setFields(key, value, OrganizationField.values());
                break;
        }
    }

}
