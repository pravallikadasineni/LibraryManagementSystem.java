import java.util.ArrayList;
import java.util.Scanner;

// Class to store book details
class Book {
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author;
    }
}

// Main class
public class LibraryManagement {
    private static ArrayList<Book> library = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Welcome to Library Management System ---");
        
        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    displayBooks();
                    break;
                case "5":
                    System.out.println("Thank you! Application terminated.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please select between 1 and 5.");
            }
        }
    }

    // 1. Method to add a book
    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();
        
        // Input validation
        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }

        // Check if ID already exists
        for (Book b : library) {
            if (b.getId().equalsIgnoreCase(id)) {
                System.out.println("Error: A book with this ID already exists!");
                return;
            }
        }

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine().trim();

        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Error: Title and Author cannot be empty!");
            return;
        }

        library.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    // 2. Method to remove a book
    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        String id = scanner.nextLine().trim();
        
        Book toRemove = null;
        for (Book b : library) {
            if (b.getId().equalsIgnoreCase(id)) {
                toRemove = b;
                break;
            }
        }

        if (toRemove != null) {
            library.remove(toRemove);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("No book found with the given ID.");
        }
    }

    // 3. Method to search for a book
    private static void searchBook() {
        System.out.print("Enter Book Title or ID to search: ");
        String query = scanner.nextLine().trim();
        boolean found = false;

        for (Book b : library) {
            if (b.getTitle().equalsIgnoreCase(query) || b.getId().equalsIgnoreCase(query)) {
                System.out.println("Book Found -> " + b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no book found with the given Title or ID.");
        }
    }

    // 4. Method to display all books
    private static void displayBooks() {
        if (library.isEmpty()) {
            System.out.println("The library is currently empty.");
            return;
        }
        System.out.println("\n--- List of Books in Library ---");
        for (Book b : library) {
            System.out.println(b);
        }
    }
}