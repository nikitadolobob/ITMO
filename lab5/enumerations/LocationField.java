package lab5.enumerations;

import lab5.Field;
import lab5.models.Location;

public enum LocationField implements Field<Location> {
    X(false) {
        @Override
        public Float get(Location location) {
            return location.getX();
        }

        @Override
        public void set(Location location, String... args) throws Exception{
            if(args.length == 0) throw new Exception("Значение x не может быть null!");
            location.setX(Float.parseFloat(args[0]));
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите вещественное чиcло: x-координату локации. Значение не может быть null.");
        }
    },
    Y(true) {
        @Override
        public Double get(Location location) {
            return location.getY();
        }

        @Override
        public void set(Location object, String... args) throws Exception {
            if(args.length == 0) return;
            object.setY(Double.parseDouble(args[0]));

        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите вещественное чиcло: y-координату локации.");
        }
    },
    Z(false){
        @Override
        public Double get(Location object) {
            return object.getZ();
        }

        @Override
        public void set(Location object, String... args) throws Exception {
            if(args.length == 0) throw new Exception("Значение z не может быть null!");
            object.setZ(Double.parseDouble(args[0]));
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите вещественное чиcло: z-координату локации.");
        }
    },
    NAME(true){
        @Override
        public String get(Location object) {
            return object.getName();
        }

        @Override
        public void set(Location object, String... args) throws Exception {
            object.setName(args[0]);
        }

        @Override
        public void writeInputMessage() {
            System.out.println("Введите строку - название локации.");
        }
    };

    public final boolean nullValid;

    LocationField(boolean nullValid) {
        this.nullValid = nullValid;
    }

    @Override
    public boolean getNullValid() {
        return nullValid;
    }

    @Override
    public void read(Location location) {
        readField(this::set, location, getNullValid());
    }
}
