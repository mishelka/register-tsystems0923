package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /** register.Register of persons. */
    private Register register;
    
    /**
     * In JDK 6 use Console class instead.
     * @see readLine()
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    };
    
    public ConsoleUI(Register register) {
        this.register = register;
    }
    
    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    printRegister();
                    break;
                case ADD:
                    addToRegister();
                    break;
                case UPDATE:
                    updateRegister();
                    break;
                case REMOVE:
                    removeFromRegister();
                    break;
                case FIND:
                    findInRegister();
                    break;
                case EXIT:
                    return;
            }
        }
    }
    
    private String readLine() {
        //In JDK 6.0 and above Console class can be used
        //return System.console().readLine();
        
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    private Option showMenu() {
        System.out.println("Menu.");
        for (Option option : Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");
        
        int selection = -1;
        do {
            System.out.println("Option: ");
            selection = Integer.parseInt(readLine());
        } while (selection <= 0 || selection > Option.values().length);
        
        return Option.values()[selection - 1];
    }
    
    //TODO: Implement the method printRegister
    private void printRegister() {
        for (int p = 0; p < register.getCount(); p++) {
            Person person = register.getPerson(p);
            System.out.printf("%d.\t%s\n", p+1, person);
        }
    }
    
    private void addToRegister() {
        System.out.println("Enter Name: ");
        String name = readLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();
        
        register.addPerson(new Person(name, phoneNumber));
    }
    
    //TODO: Implement the method updateRegister
    private void updateRegister() {
        System.out.println("Enter Name: ");
        String name = readLine().trim();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine().trim();
    }
    
    //TODO: Implement the method findInRegister
    private void findInRegister() {
        //full text search - searches for the string inside either name or surname of the person
        //better full text search could be case insensitife - use toLowerCase on both searched string and 
        System.out.println("Enter the phone number or a name to search for:");
        String strToFind = readLine().trim();

        Person[] foundPersons = new Person[register.getCount()];
        int foundPersonsCount = 0;

        for (int i = 0; i < register.getCount(); i++) {
            Person p = register.getPerson(i);
            if(p.getName().contains(strToFind)) {
                foundPersons[foundPersonsCount] = p;
                foundPersonsCount++;
                continue; //aby sme nepridali tu istu osobu dvakrat
            }
            if(p.getPhoneNumber().contains(strToFind)) {
                foundPersons[foundPersonsCount] = p;
                foundPersonsCount++;
            }
        }

        System.out.println(foundPersonsCount == 0 ? "No person found." : "The following persons were found: ");
        for (Person p: foundPersons) {
            if(p != null) {
                System.out.println(p);
            }
        }
    }
    
    private void removeFromRegister() {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(readLine().trim());
        Person person = register.getPerson(index - 1);
        register.removePerson(person);
    }
}
