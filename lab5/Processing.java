package lab5;

/**
 * Абстрактный интерфейс для обработки ввода данных пользователя
 *
 * @param <O> параметр, задающий тип данных объекта, которому мы хотим присвоить занчение
 */
public interface Processing<O>{
    /**
     * Функция, обрабатывающая ввод пользователя и присваивающаяя полю значение в случае корректного ввода
     *
     * @param object объект, полю которого нужно присвоить значение
     * @param s      значение, которое ввел пользователь
     * @throws Exception исключение, выбрасывающееся в случае некорректного ввода
     */
    void processInput(O object, String... s) throws Exception;
}
