import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contacts {

    static Scanner sc;
    static int choice;
    static String directory = "manager";
    static String filename = "contacts.txt";
    public static final Path dataDirectory = Paths.get(directory);
    public static final Path dataFile = Paths.get(directory, filename);

    public static int menu() {
        sc = new Scanner(System.in);
        System.out.println("1. View contacts\n" +
                "2. Add  a new contact\n" +
                "3. Search a contact by name\n" +
                "4. Delete an existing contact\n" +
                "5. Exit");
        System.out.print("Enter an option (1, 2, 3, 4 or 5)");

        choice = sc.nextInt();
        if(choice == 1){
            allContacts();
        }
        return choice;
    }

    public static void allContacts() {
        try{
            List<String> contacts = Files.readAllLines(dataFile);
            for(String contact: contacts){
                System.out.println(contact);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void createFile() {
        String directory = "manager";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        System.out.println(dataFile.toAbsolutePath());
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectory(dataDirectory);
            }
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

            List<String> contacts = Files.readAllLines(dataFile);
            for (String contact : contacts) {
                System.out.println(contact);
            }


//            List<String> contacts = new ArrayList<>();
//            contacts.add("Travis 210-210-2102");
//            Files.write(dataFile, contacts);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        createFile();
        menu();

    }
}
