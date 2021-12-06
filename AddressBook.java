import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    List<Entry> entries = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("1) Add an entry");
            System.out.println("2) Remove an entry");
            System.out.println("3) Search for a specific entry");
            System.out.println("4) Print Address Book");
            System.out.println("5) Delete Book");
            System.out.println("6) Quit");
            System.out.print("\nPlease choose what you'd like to do with the database: ");
            int response = Integer.parseInt(input.nextLine());
            switch (response) {
                case 1:
                    addEntry();
                    break;
                case 2:
                    removeEntry();
                    break;
                case 3:
                    searchEntry();
                    break;
                case 4:
                    printContents();
                    break;
                case 5:
                    deleteAddressBook();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }
        }
        input.close();
    }

    static void addEntry() {
        Scanner input = new Scanner(System.in);
        Entry entry = new Entry();
        System.out.print("First Name: ");
        entry.setFirstName(input.nextLine());
        System.out.print("Last Name: ");
        entry.setLastName(input.nextLine());
        System.out.print("Phone Number: ");
        entry.setPhoneNumber(input.nextLine());
        System.out.print("Email Address: ");
        entry.setEmailAddress(input.nextLine());
        System.out.println("Added new entry!");
        input.close();
    }

    static void removeEntry() {

    }

    static void searchEntry() {

    }

    static void printContents() {

    }

    static void deleteAddressBook() {

    }
}