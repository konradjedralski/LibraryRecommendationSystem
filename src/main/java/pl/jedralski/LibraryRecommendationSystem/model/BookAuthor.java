package pl.jedralski.LibraryRecommendationSystem.model;

public class BookAuthor {

    private Long id;
    private String author;

    public BookAuthor(Long id, String author) {
        this.id = id;
        this.author = author;
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
}
