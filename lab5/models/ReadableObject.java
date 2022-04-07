package lab5.models;

import lab5.Field;
import java.io.BufferedReader;

public abstract class ReadableObject<O extends ReadableObject<O, T>, T extends Enum<T> & Field<O>>{
    public static String inputFileName;
    public static BufferedReader fieldReader;

    public ReadableObject() {
    }

    O castThis(){
        return (O)this;
    }

    public static void setInputFileName(String name){
        inputFileName = name;
    }

    public static String getInputFileName(){
        return inputFileName;
    }

    public static BufferedReader getFieldReader(){
        return fieldReader;
    }

    public static void setFieldReader(BufferedReader reader){
        fieldReader = reader;
    }


    public final void setFields(String key, String value, T[] fields){
        for (T field : fields) {
            if (key.equals(field.name())) {
                try {
                    field.set(castThis(), value);
                }catch (Exception e){
                    System.out.println("Некоректный входной файл: " + e.getMessage());
                    System.exit(0);

                }
            }
        }
    }

    public final void readElement(T[] fields) {
        for (T field : fields) {
            field.read(castThis());
        }
    }

    /*public final void checkElement(T[] fields) throws Exception{
        O object = castThis();
        for(T field : fields){
            if (field.get(object) == null && !field.getNullValid()){
                throw new Exception(String.format("Значение %s не может быть null.", field.name()));
            }
        }
    }*/

}
