package lab5.models;

import lab5.interfaces.Field;
import java.io.BufferedReader;

/**
 * Объект, который можно считать с файла
 *
 * @param <O> параметр, отвечающий за тип самого объекта
 * @param <T> параметр, отвечающий за список полей объекта
 */
public abstract class ReadableObject<O extends ReadableObject<O, T>, T extends Enum<T> & Field<O>>{
    /**
     * Имя файла, с которого необходимо считывать объекты.
     */
    public static String inputFileName;
    /**
     * fieldReader, при помощи которого необходимо считывать.
     */
    public static BufferedReader fieldReader;

    /**
     * Создает новый Readable object.
     */
    public ReadableObject() {
    }

    /**
     * Приводит данный объект к параметру O.
     *
     * @return the o
     */
    O castThis(){
        return (O)this;
    }

    /**
     * Присваивает путь к входному файлу.
     *
     * @param name путь к входному файлу
     */
    public static void setInputFileName(String name){
        inputFileName = name;
    }

    /**
     * Получает путь к входному файлу.
     *
     * @return путь к входному файлу
     */
    public static String getInputFileName(){
        return inputFileName;
    }

    /**
     * Get field reader buffered reader.
     *
     * @return the buffered reader
     */
    public static BufferedReader getFieldReader(){
        return fieldReader;
    }

    /**
     * Set field reader.
     *
     * @param reader the reader
     */
    public static void setFieldReader(BufferedReader reader){
        fieldReader = reader;
    }


    /**
     * Метод, по названию поля определяющий, куда его нужно присвоить
     *
     * @param key    название поля
     * @param value  значение поля
     * @param fields список всех полей объекта
     */
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

    /**
     * Метод, отвечающий за считывание всего объекта, последовательно вызывает чтение каждого поля
     *
     * @param fields все поля объекта
     */
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
