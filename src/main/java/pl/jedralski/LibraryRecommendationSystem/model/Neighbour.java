package pl.jedralski.LibraryRecommendationSystem.model;

public class Neighbour implements Comparable<Neighbour> {

    private Long userID;
    private int sameBooks;
    private int distance;

    public Neighbour(Long userID, int sameBooks, int distance) {
        this.userID = userID;
        this.sameBooks = sameBooks;
        this.distance = distance;
    }

    public Neighbour(Long userID, int sameBooks) {
        this.userID = userID;
        this.sameBooks = sameBooks;
    }

    @Override
    public int compareTo(Neighbour o) {
        return (distance - o.distance);
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public int getSameBooks() {
        return sameBooks;
    }

    public void setSameBooks(int sameBooks) {
        this.sameBooks = sameBooks;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
