package lab5.enumerations;

import lab5.interfaces.Field;
import lab5.models.Address;
import lab5.models.Location;

/**
 * Список полей объекта Адрес
 */
public enum AdressField implements Field<Address> {
    /**
     * Почтовый индекс
     */
    ZIPCODE(true) {
        @Override
        public String get(Address adress) {
            return adress.getZipCode();
        }

        @Override
        public void set(Address address, String... args) {
            address.setZipCode(args[0]);
        }

        @Override
        public void read(Address adress) {
            readField(this::set, adress, getNullValid());
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите строку: почтовый индекс организации. Чтобы ввести пустое значение, нажмите Enter.");
        }
    },
    /**
     * Город
     */
    TOWN(true) {
        @Override
        public Location get(Address adress) {
            return adress.getTown();
        }


        @Override
        public void set(Address address, String... args) {
            Location town = new Location();
            town.readElement(LocationField.values());
            address.setTown(town);
        }

        @Override
        public void read(Address adress) {
            writeInputMessage();
            set(adress);
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите город, в котором находится организация. Для того, чтобы оставить поле \"Город\" пустым, оставьте пустыми все его поля.");
        }
    };

    /**
     * Поле, указывающее, может ли поле быть null
     */
    public final boolean nullValid;

    /**
     * Создает новый Adress field.
     *
     * @param nullValid поле, показывающее, может ли поле быть null
     */
    AdressField(boolean nullValid) {
        this.nullValid = nullValid;
    }


    @Override
    public boolean getNullValid() {
        return nullValid;
    }

}
