import java.util.Date;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int year;
    private int pages;
    private String status;
    private Patron patron;
    private Date due;

    public static final String AVAILABLE = "AVAILABLE";
    public static final String UNAVAILABLE = "UNAVAILABLE";

    public Book(String title, String author, String isbn, int year, int pages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.pages = pages;
        this.status = AVAILABLE;
        this.patron = null;
        this.due = null;
    }

    public void checkout(Patron patron, Date due) {
        this.status = UNAVAILABLE;
        this.patron = patron;
        this.due = due;
    }

    public void checkin() {
        this.status = AVAILABLE;
        this.patron = null;
        this.due = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getStatus() {
        return status;
    }

    public Patron getPatron() {
        return patron;
    }

    public Date getDue() {
        return due;
    }

    public boolean equals(Object other) {
        if (other instanceof Book) {
            Book otherBook = (Book) other;
            return this.isbn.equals(otherBook.isbn);
        } else if (other instanceof String) {
            return this.isbn.equals(other);
        }
        return false;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Year: " + year + ", Pages: " + pages + ".";
    }
}
