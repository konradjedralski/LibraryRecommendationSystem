package pl.jedralski.LibraryRecommendationSystem.model;

public class Book implements Comparable<Book> {

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
    private double ratingRecommended;
    private int usersNumber;

    public Book(Long id, String author, String publisher, String genre, String isbn, String title, short year, String imageL) {
        this.id = id;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.imageL = imageL;
    }

    public Book(Long id, String title, String imageM, short rating) {
        this.id = id;
        this.title = title;
        this.imageM = imageM;
        this.rating = rating;
    }

    public Book(Long id, String title, String imageL) {
        this.id = id;
        this.title = title;
        this.imageL = imageL;
    }

    public Book(Long id, String title, String author, String imageM) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.imageM = imageM;
    }

    public Book(Long id, String imageL) {
        this.id = id;
        this.imageL = imageL;
    }

    public Book(Long id, short rating) {
        this.id = id;
        this.rating = rating;
    }

    public Book(Long id, double ratingRecommended) {
        this.id = id;
        this.ratingRecommended = ratingRecommended;
    }

    public Book(int usersNumber, double ratingRecommended) {
        this.usersNumber = usersNumber;
        this.ratingRecommended = ratingRecommended;
    }

    @Override
    public int compareTo(Book o) {
        if (o.ratingRecommended - this.ratingRecommended < 0) return -1;
        else if (o.ratingRecommended - this.ratingRecommended > 0) return 1;
        else return 0;
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

    public double getRatingRecommended() {
        return ratingRecommended;
    }

    public void setRatingRecommended(double ratingRecommended) {
        this.ratingRecommended = ratingRecommended;
    }

    public int getUsersNumber() {
        return usersNumber;
    }

    public void setUsersNumber(int usersNumber) {
        this.usersNumber = usersNumber;
    }
}
