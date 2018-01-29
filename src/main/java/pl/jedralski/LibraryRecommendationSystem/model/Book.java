package pl.jedralski.LibraryRecommendationSystem.model;

public class Book {

    private Long id;
    private String author;
    private String publisher;
    private String genre;
    private String isbn;
    private String title;
    private short year;
    private short availability;
    private String imageS;
    private String imageM;
    private String imageL;
    private short rating;


    public Book(Long id, String author, String publisher, String genre, String isbn, String title, short year, short availability, String imageS, String imageM, String imageL) {
        this.id = id;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.availability = availability;
        this.imageS = imageS;
        this.imageM = imageM;
        this.imageL = imageL;
    }

    public Book(String author, String publisher, String genre, String isbn, String title, short year, String imageL) {
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.imageL = imageL;
    }

    public Book(String title, String imageM, short rating) {
        this.title = title;
        this.imageM = imageM;
        this.rating = rating;
    }

    public Book(String title, String imageL) {
        this.title = title;
        this.imageL = imageL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getAvailability() {
        return availability;
    }

    public void setAvailability(short availability) {
        this.availability = availability;
    }

    public String getImageS() {
        return imageS;
    }

    public void setImageS(String imageS) {
        this.imageS = imageS;
    }

    public String getImageM() {
        return imageM;
    }

    public void setImageM(String imageM) {
        this.imageM = imageM;
    }

    public String getImageL() {
        return imageL;
    }

    public void setImageL(String imageL) {
        this.imageL = imageL;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
