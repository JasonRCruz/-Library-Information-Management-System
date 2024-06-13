import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Driver {

    public static Patron[] loadPatrons(String fileName) {
        ArrayList<Patron> patronList = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(fileName));
            while (inFile.hasNextLine()) {
                String[] terms = inFile.nextLine().split(",");
                Patron patron = new Patron(terms[0], terms[1], Integer.parseInt(terms[2].trim()), Double.parseDouble(terms[3].trim()));
                patronList.add(patron);
            }
        } catch (IOException e) {
            System.out.println("Error reading patron file: " + fileName);
            System.exit(0);
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
        return patronList.toArray(new Patron[0]);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Book[] catalog = loadBooks("books.txt");
        Patron[] patrons = loadPatrons("patrons.txt");
        Library library = new Library(catalog, patrons);

        char option;
        do {
            System.out.print("Choose an option: ");
            option = input.nextLine().charAt(0);

            if (option == 'S') {
                System.out.print("Enter Title: ");
                String searchTitle = input.nextLine();
                for (Book b : catalog) {
                    if (searchTitle.equals(b.getTitle())) {
                        System.out.println(b.toString());
                        System.out.println("Status: " + b.getStatus());
                        System.out.println("Due: " + b.getDue());
                        System.out.println("Patron: " + b.getPatron());
                    }
                }
            } else if (option == 'O') {
                Book book = null;
                Patron patron = null;

                System.out.print("Enter ISBN: ");
                String isbn = input.nextLine();
                for (Book b : catalog) {
                    if (isbn.equals(b.getIsbn())) {
                        book = b;
                    }
                }
                if (book == null) {
                    System.out.println("That ISBN is not in the catalog.");
                    continue;
                }

                System.out.print("Enter Patron Nr: ");
                Integer patronNr = Integer.parseInt(input.nextLine());
                for (Patron p : patrons) {
                    if (p.equals(patronNr)) {
                        patron = p;
                    }
                }
                if (patron == null) {
                    System.out.println("That patron number does not exist.");
                    continue;
                }

                library.checkout(book, patron);
            } else if (option == 'I') {
                Book book = null;

                System.out.print("Enter ISBN: ");
                String isbn = input.nextLine();
                for (Book b : catalog) {
                    if (isbn.equals(b.getIsbn())) {
                        book = b;
                    }
                }
                if (book == null) {
                    System.out.println("That ISBN is not in the catalog.");
                    continue;
                }

                library.checkin(book);
            } else if (option == 'F') {
                Book book = null;

                System.out.print("Enter ISBN: ");
                String isbn = input.nextLine();
                for (Book b : catalog) {
                    if (isbn.equals(b.getIsbn())) {
                        book = b;
                    }
                }
                if (book == null) {
                    System.out.println("That ISBN is not in the catalog.");
                    continue;
                }

                System.out.println("Fine: $" + library.determineFine(book));
            }
        } while (option != 'Q');
    }

    public static Book[] loadBooks(String fileName) {
        ArrayList<Book> bookList = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(fileName));
            while (inFile.hasNextLine()) {
                String[] terms = inFile.nextLine().split(",");
                Book book = new Book(terms[0], terms[1], terms[2], Integer.parseInt(terms[3].trim()), Integer.parseInt(terms[4].trim()));
                bookList.add(book);
            }
        } catch (IOException e) {
            System.out.println("Error reading book file: " + fileName);
            System.exit(0);
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
        return bookList.toArray(new Book[0]);
    }
}
