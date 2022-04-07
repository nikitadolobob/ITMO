package lab5;

public interface Processing<O>{
    void processInput(O object, String... s) throws Exception;
}
