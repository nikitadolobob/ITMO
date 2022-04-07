package lab5;


import lab5.models.ReadableObject;

import java.io.*;

public interface Field<O>{

    <T> T get(O object);
    void set(O object, String... args) throws Exception;
    void read(O object);
    void writeInputMessage();
    boolean getNullValid();
    default void readField(Processing<O> processing, O object, boolean nullValid) {
        BufferedReader reader = ReadableObject.getFieldReader();
        String value = null;
        while (value == null) {
            writeInputMessage();
            try {
                value = reader.readLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(value != null && value.isEmpty()){
                if(nullValid) return;
                else value = null;
            }

            try {
                processing.processInput(object, value);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                value = null;
            }
        }
    }
}