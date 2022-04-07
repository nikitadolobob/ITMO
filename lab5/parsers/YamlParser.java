package lab5.parsers;

import lab5.models.Organization;

import java.util.Hashtable;

public abstract class YamlParser {
    protected String filePath;
    protected Hashtable<Integer, Organization> organizations;

    public YamlParser(Hashtable<Integer, Organization> o, String filePath){
        this.organizations = o;
        this.filePath = filePath;
    }
    protected void openFile(){}
}
