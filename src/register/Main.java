package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Register register = new Register(20);

        register.addPerson(new Person("Janko Hrasko", "+421900123456"));
        register.addPerson(new Person("Jano Hrach", "+421900123456"));
        register.addPerson(new Person("Chester Bennington", "+421900123456"));
        register.addPerson(new Person("Mike Shinoda", "+421900123456"));
        register.addPerson(new Person("Arnold Schwarzenegger", "+421900123456"));

        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
    }
}
