package pl.jedralski.LibraryRecommendationSystem.model;

public class BookRating {

    private Long id;
    private Long userId;
    private Long bookId;
    private short rating;

    public BookRating(Long id, Long userId, Long bookId, short rating) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
