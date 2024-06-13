import java.util.Date;

public class Library {
    public static final char AUTHOR_SEARCH = 'A';
    public static final char OVERDUE_SEARCH = 'O';
    public static final char PATRON_SEARCH = 'P';
    public static final char TITLE_SEARCH = 'T';

    private Book[] books;
    private Patron[] patrons;

    public Library(Book[] books, Patron[] patrons) {
        this.books = books;
        this.patrons = patrons;
    }

    public boolean checkin(Book book) {
        if (book != null) {
            for (Book item : books) {
                if (item.equals(book)) {
                    if (item.getStatus() == Book.UNAVAILABLE) {
                        double fine = determineFine(book);
                        book.getPatron().adjustBalance(fine);
                        book.checkin();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkout(Book book, Patron patron) {
        if (book != null && patron != null) {
            for (Book item : books) {
                if (item.equals(book)) {
                    if (item.getStatus() == Book.AVAILABLE) {
                        Date due = DateUtils.addDays(new Date(), 10);
                        book.checkout(patron, due);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public double determineFine(Book book) {
        if (book.getDue() != null) {
            int days = DateUtils.interval(book.getDue(), new Date());
            if (days > 0) {
                return days * 0.5;
            }
        }
        return 0.0;
    }

    public Book[] searchBooks(Object key, char type) {
        int count = 0;
        for (Book b : books) {
            if ((type == TITLE_SEARCH && b.getTitle().equals(key)) ||
                (type == AUTHOR_SEARCH && b.getAuthor().equals(key)) ||
                (type == PATRON_SEARCH && b.getPatron() != null && b.getPatron().equals(key)) ||
                (type == OVERDUE_SEARCH && b.getDue() != null && key instanceof Date && DateUtils.interval(b.getDue(), (Date) key) > 0)) {
                count++;
            }
        }

        Book[] booksFound = new Book[count];
        int i = 0;
        for (Book b : books) {
            if ((type == TITLE_SEARCH && b.getTitle().equals(key)) ||
                (type == AUTHOR_SEARCH && b.getAuthor().equals(key)) ||
                (type == PATRON_SEARCH && b.getPatron() != null && b.getPatron().equals(key)) ||
                (type == OVERDUE_SEARCH && b.getDue() != null && key instanceof Date && DateUtils.interval(b.getDue(), (Date) key) > 0)) {
                booksFound[i++] = b;
            }
        }
        return booksFound;
    }
}
