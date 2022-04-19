package lab5.parsers;

import lab5.enumerations.AdressField;
import lab5.enumerations.CoordinatesField;
import lab5.enumerations.LocationField;
import lab5.interfaces.Field;
import lab5.models.Organization;
import lab5.enumerations.OrganizationField;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Класс, осуществляющий запись в YAML
 */
public class YamlWriter extends YamlParser {
    /**
     * Writer, записывающий данные в файл.
     */
    private BufferedOutputStream writer;

    /**
     * Создает новый Yaml writer.
     *
     * @param organizations коллекция организаций
     * @param filePath      yaml-файл для записи
     */
    public YamlWriter(Hashtable<Integer, Organization> organizations, String filePath) {
        super(organizations, filePath);
    }

    @Override
    protected void openFile(){
        OutputStream outputStream = null;
        try {
            outputStream = Files.newOutputStream(Paths.get(filePath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (outputStream != null) {
            writer = new BufferedOutputStream(outputStream);
        }
    }

    /**
     * Метод, переводящий текст в байты и записывающий его в файл.
     *
     * @param text текст для записи в файл
     */
    public void write(String text){
        //TODO почему не работает запись в файл?
        byte[] buffer = text.getBytes();
        try {
            if (writer != null) {;
                writer.write(buffer, 0, buffer.length);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Записывает в файл все поля одного объекта
     *
     * @param <O>    тип объекта
     * @param <T>    тип его полей
     * @param object объект для записи
     * @param fields список его полей
     */
    private <O, T extends Enum<T> & Field<O>> void writeObject(O object, T [] fields){
        write("{\n");
        for(T field : fields){
            switch (field.name()) {
                case "COORDINATES":
                    writeObject(field.get(object), CoordinatesField.values());
                    break;
                case "ADRESS":
                    writeObject(field.get(object), AdressField.values());
                    break;
                case "TOWN":
                    writeObject(field.get(object), LocationField.values());
                    break;
                default:
                    write(field.name() + ":" + field.get(object));
                    break;
            }
        }
        write("}\n");
    }

    /**
     * Метод, запускающий процесс записи данных
     */
    public void writeData(){
        write("{\n\"Organizations\": [\n");
        Enumeration<Organization> e = organizations.elements();
        while (e.hasMoreElements()) {
            Organization organization = e.nextElement();
            writeObject(organization, OrganizationField.values());
        }
        write("]\n}");

    }
}
