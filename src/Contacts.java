import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Contacts {

    private static Scanner sc;
    private static int choice;
    private static String directory = "manager";
    private static String filename = "contacts.txt";
    private static final Path dataDirectory = Paths.get(directory);
    private static final Path dataFile = Paths.get(directory, filename);

    private static List<String> contacts = new ArrayList<>();

    public static int menu() {
        sc = new Scanner(System.in);
        System.out.println("1. View contacts\n" +
                "2. Add a new contact\n" +
                "3. Search a contact by name\n" +
                "4. Delete an existing contact\n" +
                "5. Exit");
        System.out.print("Enter an option (1, 2, 3, 4 or 5)");

        choice = sc.nextInt();
        if (choice == 1) {
            allContacts();
            menu();
        } else if (choice == 2) {
            addContacts();
            menu();
        } else if (choice == 3) {
            findContact();
            menu();
        }else if (choice == 4) {
            deleteContact();
            menu();
        }
        return choice;
    }

    public static void allContacts() {

        try {
            List<String> contacts = Files.readAllLines(dataFile);
            System.out.println();

            for (String contact : contacts) {
                System.out.println(contact);
            }

            System.out.println();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addContacts() {

        System.out.println("Enter name and number of new contact: ");
        sc.nextLine();
        try {
            Files.write(
                    Paths.get("manager", "contacts.txt"),
                    Arrays.asList(sc.nextLine()),
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Add another contact? [y/n] ");
        if (sc.next().equals("y")) {
            addContacts();
            sc.nextLine();
        } else if (sc.nextLine().equals("n")) {
            menu();
            sc.nextLine();
        }
    }

    public static void createFile() {
        String directory = "manager";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectory(dataDirectory);
            }
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void findContact() {

        System.out.println("Who are you looking for? ");
        sc.nextLine();
        String nameToFind = sc.nextLine();

        try {
            for (String contact : contacts) {
                if (contact.equals(dataFile)) {
                    System.out.println(contact);
                    List<String> contacts = Files.readAllLines(dataFile);
                    System.out.println(contact);

//            for(String contact: contacts){
//                System.out.println(contact);

//                if (contact.equals(nameToFind)) {
//                    System.out.println(contact);
                }
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void deleteContact() {
        System.out.println("Do you wish to delete the contact?");
        sc.nextLine();
        String nameToDelete = sc.nextLine();

        try {

            List<String> contacts = Files.readAllLines(dataFile);
            System.out.println(contacts);

            if (sc.next().equals("y")) {
                deleteContact();
                for(String contact: contacts);
                sc.nextLine();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        createFile();
        menu();
    }
}
