package lab5;

import lab5.models.Organization;
import lab5.parsers.YamlReader;

import java.util.Hashtable;

public class Main {

    private static final Hashtable<Integer,Organization> organizations = new Hashtable<>();

    public static void main(String[] arg){

        //System.out.println(System.getenv("PATH"));
        String filename = ""; //TODO переменная окружения

        YamlReader inputFileReader = new YamlReader(organizations, filename);
        inputFileReader.readData();
        UserInteraction userInteraction = new UserInteraction(organizations);
        userInteraction.start();

    }
}
