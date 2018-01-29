package pl.jedralski.LibraryRecommendationSystem.model;

public class BookBorrowed {

    private Long userId;
    private Long bookId;
    private boolean active;

    public BookBorrowed(Long userId, Long book_id, boolean active) {
        this.userId = userId;
        this.bookId = book_id;
        this.active = active;
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

    public void setBookId(Long book_id) {
        this.bookId = book_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
