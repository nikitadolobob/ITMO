package lab5.parsers;

import lab5.models.Organization;

import java.util.Hashtable;

/**
 * Класс, работающий с YAML файлом
 */
public abstract class YamlParser {
    /**
     * Путь к файлу
     */
    protected String filePath;
    /**
     * Коллекция, с которой необходимо работать
     */
    protected Hashtable<Integer, Organization> organizations;

    /**
     * Конструктор, создающий новый парсер
     *
     * @param o        коллекция, с которой необходимо работать
     * @param filePath путь к файлу
     */
    public YamlParser(Hashtable<Integer, Organization> o, String filePath){
        this.organizations = o;
        this.filePath = filePath;
    }

    /**
     * Метод, открывающий заданный файл
     */
    protected void openFile(){}
}
