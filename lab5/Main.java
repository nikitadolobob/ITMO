package lab5;

import lab5.models.Organization;
import lab5.parsers.YamlReader;

import java.util.Hashtable;

/**
 * Класс Main.
 */
public class Main {

    private static final Hashtable<Integer,Organization> organizations = new Hashtable<>();

    public static String inputFile;

    /**
     * Функция, запускающая приложение.
     *
     * @param arg аргументы, которые программа получает при запуске.
     */
    public static void main(String[] arg){

        inputFile = "C:\\Users\\myav\\IdeaProjects\\ITMO_labs\\src\\lab5\\resources\\zhest.json"; //TODO переменная окружения

        YamlReader inputFileReader = new YamlReader(organizations, inputFile);
        inputFileReader.readData();
        UserInteraction userInteraction = new UserInteraction(organizations);
        userInteraction.start();

    }
}
