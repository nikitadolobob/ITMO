package lab5.parsers;

import lab5.models.Organization;
import lab5.enumerations.OrganizationField;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class YamlWriter extends YamlParser {
    private BufferedOutputStream writer;
    public YamlWriter(Hashtable<Integer, Organization> organizations, String filePath) {
        super(organizations, filePath);
    }

    @Override
    protected void openFile(){
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (outputStream != null) {
            writer = new BufferedOutputStream(outputStream);
        }
    }

    public void write(String text){

        byte[] buffer = text.getBytes();
        try {
            if (writer != null) {
                writer.write(buffer, 0, buffer.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void writeData(){
        write("{\n\"Organizations\": [\n");
        Enumeration<Organization> e = organizations.elements();
        while (e.hasMoreElements()) {
            write("{\n");
            Organization organization = e.nextElement();
            for(OrganizationField field : OrganizationField.values()){
                write(field.name() + ":" + field.get(organization));
            }
            write("}\n");
        }
        write("]\n}");

    }
}
