import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class Contacts {

    private static Scanner sc = new Scanner(System.in);
    private static int choice;
    private static String directory = "manager";
    private static String filename = "contacts.txt";
    private static final Path dataDirectory = Paths.get(directory);
    private static final Path dataFile = Paths.get(directory, filename);

    private static List<String> contacts = new ArrayList<>();


    public static int menu() {
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
        } else if (choice == 4) {
            deleteContact();
            menu();
        }
        return choice;
    }

    public static void allContacts() {
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }

    public static void loadContacts() {
        try {
            contacts = Files.readAllLines(dataFile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addContacts() {

        System.out.println("Enter name and number of new contact: ");
        sc.nextLine();

        contacts.add(sc.nextLine());
        persistContacts();

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
        String name = sc.nextLine();

        for (String contact : contacts) {
            if (contact.toLowerCase().contains(name.toLowerCase())) {
                System.out.println(contact);
            }
        }
    }

    public static void deleteContact() {

        for(int i = 0; i < contacts.size();i++){
            System.out.println(i + " " + contacts.get(i));
        }

        System.out.println("Enter the number of the contact you wish to delete: ");
        int x = sc.nextInt();
        contacts.remove(x);
        persistContacts();
        System.out.println("Contact deleted successfully.");

    }

    public static void persistContacts() {
        try {
            Files.write(dataFile, contacts, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        createFile();
        loadContacts();
        menu();
    }
}
