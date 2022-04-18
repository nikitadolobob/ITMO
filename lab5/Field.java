package lab5;


import lab5.models.ReadableObject;

import java.io.*;

/**
 * Инетрфейс Field, определящий набор методов, которые можно применить к полю
 *
 * @param <O> параметр, задающий тип данных объекта, с полем которого мы работаем
 */
public interface Field<O>{

    /**
     * Метод для получения значения поля
     *
     * @param <T>    параметр, отвечающий за тип данных поля, значение которого необходимо вернуть
     * @param object объект, значение поля которого необходимо вернуть
     * @return значение поля
     */
    <T> T get(O object);

    /**
     * Метод для присвоения значения полю
     *
     * @param object объект, значение поля которого необходимо присвоить
     * @param args   значение, которое необходимо присвоить полю
     * @throws Exception исключение, выбрасывающееся в случае попытки присвоить некорректное значение
     */
    void set(O object, String... args) throws Exception;

    /**
     * Метод, отвечающий за считывание значения поля
     *
     * @param object объект, значение поля которого необходимо считать
     */
    void read(O object);

    /**
     * Метод, выводящий приглашение к вводу данного поля пользователем
     */
    void writeInputMessage();

    /**
     * Метод, возвращающий, может ли данное поле быть null
     *
     * @return значение true, если поле может быть null, и значение false иначе
     */
    boolean getNullValid();

    /**
     * Метод для считывания значения, которое вводит пользователь
     *
     * @param processing метод для обработки введенного значения
     * @param object     объект, значение поля которого считывается
     * @param nullValid  значение, указывающее на то, может ли поле быть null
     */
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