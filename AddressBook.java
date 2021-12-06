import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AddressBook {
    static Scanner input = new Scanner(System.in); // Global scanner since many methods use it
    static ArrayList<Entry> entries = new ArrayList<Entry>();

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
                    isRunning = false; // This will cancel while loop and end program
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }

        // Writes to a file
        try {
            FileWriter writer = new FileWriter("test.txt");
            int count = 1;
            for (Entry ent : entries) {
                writer.write("Entry " + count + System.lineSeparator());
                writer.write("First Name: " + ent.getFirstName() + System.lineSeparator());
                writer.write("Last Name: " + ent.getLastName() + System.lineSeparator());
                writer.write("Phone Number: " + ent.getPhoneNumber() + System.lineSeparator());
                writer.write(
                        "Email Address: " + ent.getEmailAddress() + System.lineSeparator() + System.lineSeparator());
                count++;
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates instance of Entry and adds into ArrayList
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

        // Checks if the phone number and email address is a valid format
        if (verifyEntry(entry)) {
            entries.add(entry);
            System.out.println("\nAdded new entry!");
        } else {
            System.out.println("Please enter a valid phone number and/or email address");
        }
    }

    // Nesting methods for clarity on the phone number and email checks
    static boolean verifyEntry(Entry entry) {
        if (isValidPhone(entry.getPhoneNumber()) && isValidEmail(entry.getEmailAddress())) {
            return true;
        }
        return false;
    }

    // Checks for valid Email Address
    static boolean isValidEmail(String email) {
        // Regex for Email format
        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
                + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern p = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return p.matcher(email).matches();
    }

    // Checks for valid Phone Number
    static boolean isValidPhone(String phoneNumber) {
        // Regex for Phone Number format
        String phoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        return phoneNumber.matches(phoneRegex);
    }

    // Gets email address for the deleting method
    static void removeEntry() {
        System.out.print("\nEnter an entry's email to remove: ");
        String email = input.nextLine();
        // Again, nesting methods for clarity
        deleteEntry(email);
    }

    // Deletes a single entry based on email address
    static void deleteEntry(String email) {
        // The contains(), indexOf(), starts with() ways weren't working for me, so I
        // did it the more complicated way
        for (Iterator<Entry> iterator = entries.iterator(); iterator.hasNext();) {
            Entry ent = iterator.next();
            if (ent.getEmailAddress().equals(email)) {
                iterator.remove();
            }
        }
    }

    // Getting info for the search method
    static void searchEntry() {
        boolean isFound = false;

        while (!isFound) {
            System.out.println("\n1) First Name");
            System.out.println("2) Last Name");
            System.out.println("3) Phone Number");
            System.out.println("4) Email Address");
            System.out.print("Choose a search type: ");
            int searchCriteria = Integer.parseInt(input.nextLine());
            System.out.print("Enter your search: "); // Getting the search statement before running the switch got rid
                                                     // of redundancy in code
            String search = input.nextLine();

            switch (searchCriteria) {
                // Doesn't matter what search method, so all 4 handled at once
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

    // Search method
    static void iterableSearch(int searchType, String search) {
        ArrayList<Entry> tempEntry = new ArrayList<>();
        int count = 1; // Adding a count to make it more readable, NOT AN INDEX VALUE

        // Goes through and adds all matches into a temporary ArrayList
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

        // Prints contents of tempEntry
        // Look at getting rid of 174-185 in favor of the method printContents()
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

    // Prints arrayList
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

    // Deletes everything
    static void deleteAddressBook() {
        entries.clear();
        System.out.println("\nAddress book cleared!");
    }
}