package lab5.enumerations;

import lab5.interfaces.Field;
import lab5.models.Coordinates;

/**
 * Список полей объекта Координаты
 */
public enum CoordinatesField implements Field<Coordinates> {
    /**
     * X координата.
     */
    X(false) {

        @Override
        public Double get(Coordinates coordinates) {
            return coordinates.getX();
        }

        @Override
        public void set(Coordinates coordinates, String... args) throws Exception {
            for (String value : args) {
                double x = Double.parseDouble(value);
                if (x > 280)
                    throw new Exception(String.format("Значение поля %s не может быть больше 280!", this.name()));
                coordinates.setX(x);
                break;
            }
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите вещественное число: x-координату организации. Она не должна превышать 280.");
        }
    },
    /**
     * Y координата.
     */
    Y(false) {
        @Override
        public Float get(Coordinates coordinates) {
            return coordinates.getY();
        }

        @Override
        public void set(Coordinates coordinates, String... args) throws Exception {
            for (String value : args) {
                float y = Float.parseFloat(value);
                coordinates.setY(y);
                break;
            }
        }



        @Override
        public void writeInputMessage() {
            System.out.println("Введите вещественное число: y-координату организации.");
        }

    };
    /**
     * Поле, показывающее, может ли поле быть null.
     */
    public final boolean nullValid;

    /**
     * Создает новый Coordinates field.
     *
     * @param nullValid поле, показывающее, может ли поле быть null
     */
    CoordinatesField(boolean nullValid) {
        this.nullValid = nullValid;
    }

    @Override
    public boolean getNullValid() {
        return nullValid;
    }

    @Override
    public void read(Coordinates coordinates) {
        readField(this::set, coordinates, false);
    }
}
