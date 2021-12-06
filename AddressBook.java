import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressBook {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Entry> entries = new ArrayList<>();

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
        if (verifyEntry(entry)) {
            entries.add(entry);
            System.out.println("Added new entry!");
        } else {
            System.out.println("Please enter a valid phone number and/or email address");
        }
    }

    static boolean verifyEntry(Entry entry) {
        if (isValidPhone(entry.getPhoneNumber()) && isValidEmail(entry.getEmailAddress())) {
            return true;
        }
        return false;
    }

    static boolean isValidEmail(String s) {
        String emailFormat = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
                + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern p = Pattern.compile(emailFormat);
        if (s == null) {
            return false;
        }
        return p.matcher(s).matches();
    }

    static boolean isValidPhone(String s) {
        String phoneFormat = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        return s.matches(phoneFormat);
        // Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        // Matcher m = p.matcher(s);
        // return (m.find() && m.group().equals(s));
    }

    static void removeEntry() {
        System.out.print("\nEnter an entry's email to remove: ");
        String email = input.nextLine();
        deleteEntry(email);
    }

    static void deleteEntry(String email) {
        for (Iterator<Entry> iterator = entries.iterator(); iterator.hasNext();) {
            Entry ent = iterator.next();
            if (ent.getEmailAddress().equals(email)) {
                iterator.remove();
            }
        }
    }

    static void searchEntry() {
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
                case 2:
                case 3:
                case 4:
                    iterableSearch(searchCriteria, search);
                    isFound = true;
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        }
    }

    static void iterableSearch(int searchType, String search) {
        ArrayList<Entry> tempEntry = new ArrayList<>();
        int count = 1;
        for (Iterator<Entry> iterator = entries.iterator(); iterator.hasNext();) {
            Entry ent = iterator.next();
            switch (searchType) {
                case 1:
                    if (ent.getFirstName().startsWith(search)) {
                        tempEntry.add(ent);
                    }
                    break;
                case 2:
                    if (ent.getLastName().startsWith(search)) {
                        tempEntry.add(ent);
                    }
                    break;
                case 3:
                    if (ent.getPhoneNumber().startsWith(search)) {
                        tempEntry.add(ent);
                    }
                    break;
                case 4:
                    if (ent.getEmailAddress().startsWith(search)) {
                        tempEntry.add(ent);
                    }
                    break;
            }
        }
        if (!tempEntry.isEmpty()) {
            for (Entry entry : tempEntry) {
                System.out.println("\n*******************");
                System.out.println("Entry " + count);
                System.out.println("First Name: " + entry.getFirstName());
                System.out.println("Last Name: " + entry.getLastName());
                System.out.println("Phone Number: " + entry.getPhoneNumber());
                System.out.println("Email address: " + entry.getEmailAddress());
                System.out.println("*******************");
                count++;
            }
        }
    }

    static void printContents() {
        if (!entries.isEmpty()) {
            int count = 1;
            for (Entry entry : entries) {
                System.out.println("\n*******************");
                System.out.println("Entry " + count);
                System.out.println("First Name: " + entry.getFirstName());
                System.out.println("Last Name: " + entry.getLastName());
                System.out.println("Phone Number: " + entry.getPhoneNumber());
                System.out.println("Email address: " + entry.getEmailAddress());
                System.out.println("*******************");
                count++;
            }
        }
    }

    static void deleteAddressBook() {
        entries.clear();
        System.out.println("\nAddress book cleared!");
    }
}