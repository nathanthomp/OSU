package components.book; // Remove this

public class Book {

    public Book(String[] bookDetails) {
        this.name = bookDetails[0];
        this.author = bookDetails[1];
        this.userRating = Double.parseDouble(bookDetails[2]);
        this.reviews = Integer.parseInt(bookDetails[3]);
        this.price = Double.parseDouble(bookDetails[4]);
        this.year = Integer.parseInt(bookDetails[5]);
        this.genre = bookDetails[6];
    }

    private String name;

    private String author;

    private double userRating;

    private int reviews;

    private double price;

    private int year;

    private String genre;

    public String name() {
        return this.name;
    }

    public String author() {
        return this.author;
    }

    public double userRating() {
        return this.userRating;
    }

    public int reviews() {
        return this.reviews;
    }

    public double price() {
        return this.price;
    }

    public int year() {
        return this.year;
    }

    public String genre() {
        return this.genre;
    }

}
