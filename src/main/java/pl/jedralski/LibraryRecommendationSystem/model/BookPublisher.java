package pl.jedralski.LibraryRecommendationSystem.model;

public class BookPublisher {

    private Long id;
    private String publisher;

    public BookPublisher(Long id, String publisher) {
        this.id = id;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
