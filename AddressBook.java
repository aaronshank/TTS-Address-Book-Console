import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    public static Scanner input = new Scanner(System.in);
    static List<Entry> entries = new ArrayList<>();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1) Add an entry");
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
            }
        }
        input.close();
    }

    static void addEntry() {
        Entry entry = new Entry();
        System.out.print("\nFirst Name: ");
        entry.setFirstName(input.nextLine());
        System.out.print("Last Name: ");
        entry.setLastName(input.nextLine());
        System.out.print("Phone Number: ");
        entry.setPhoneNumber(input.nextLine());
        System.out.print("Email Address: ");
        entry.setEmailAddress(input.nextLine());
        System.out.println("Added new entry!");
        entries.add(entry);
    }

    static void removeEntry() {
        Entry entry = new Entry();
        System.out.print("\nEnter an entry's email to remove: ");
        String email = input.nextLine();
        entries.remove(entries.indexOf(entry.getEmailAddress() == email));
    }

    static void searchEntry() {
        Entry entry = new Entry();
        boolean isFound = false;
        while (!isFound) {
            System.out.println("\n1) First Name");
            System.out.println("2) Last Name");
            System.out.println("3) Phone Number");
            System.out.println("4) Email Address");
            System.out.print("Choose a search type: ");
            int searchCriteria = Integer.parseInt(input.nextLine());
            System.out.print("Enter your search: ");
            String search = input.nextLine();
            switch (searchCriteria) {
                case 1:
                    //entries.contains(entry.getFirstName().startsWith(search));
                    isFound = true;
                    break;
                case 2:
                    isFound = true;
                    break;
                case 3:
                    isFound = true;
                    break;
                case 4:
                    isFound = true;
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        }
    }

    static void printContents() {
        int count = 1;
        for(Entry entry : entries) {
            System.out.println("*******************");
            System.out.println("Entry " + count);
            System.out.println("First Name: " + entry.getFirstName());
            System.out.println("Last Name: " + entry.getLastName());
            System.out.println("Phone Number: " + entry.getPhoneNumber());
            System.out.println("Email address: " + entry.getEmailAddress());
            System.out.println("*******************");
            count++;
        }
    }

    static void deleteAddressBook() {
        entries.clear();
        System.out.println("Address book cleared!");
    }
}