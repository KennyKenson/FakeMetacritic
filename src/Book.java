public class Book extends Media {

    private int pages;
    private String author;

    public Book(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus,
                String description, String publishing, int pages, String author) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.pages = pages;
        this.author = author;
    }

    public Book(String id, String name) {
        super(id, name);
        this.pages = 0;
        this.author = "N/A";
    }

    public Book(String id, String name, String author, int pages, int age, String description, String publishing) {
        super(id, name, age, description, publishing);
        this.pages = pages;
        this.author = author;
    }

    public Book() {
        super();
        this.pages = 0;
        this.author = "N/A";
    }

    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void printDetails() {
        String stars = "   ";
        if (rating == -1) {
            stars = "No rating";
        } else {
            for (int i = 0; i < rating; i++) {
                stars += "*";
            }
        }
        String age;
        if (targetAge == 0) {
            age = "No age given";
        } else {
            age = Integer.toString(targetAge);
        }

        String formatted_name;

        if (name.length() > 18) {
            formatted_name = name.substring(0, 15) + "...";
        } else {
            formatted_name = name;
        }
        System.out.printf("║%-18s║%-18s║    Book    ║%-14s║%-12s║%-12s║%-9s║\n",
                formatted_name, id, age, stars, getWatchedStatus(), isLocked);
    }

    @Override
    public String toString() {
        String lockedTitle = "";
        String tagsList = "";

        String score = "";
        if (isLocked) {
            lockedTitle = "------------------[LOCKED TITLE]------------------";
        }
        if (!tags.isEmpty()) {
            tagsList = tags.get(0);
            for (int i = 1; i < tags.size(); i++) {
                tagsList += ", " + tags.get(i);
            }
        }

        if (rating != -1) {
            for (int i = 0; i < rating; i++) {
                score += "★";
            }

            for (int i = 0; i < 5 - rating; i++) {
                score += "☆";
            }
        } else {
            score = "No rating";
        }

        return String.format("""

                        Book Viewer: %s
                        %s
                        Name: %s
                        Author: %s
                        Pages: %s pages
                        Publishing House: %s
                        Target Age: %s+
                        Status: %s
                        Tags: %s
                                        
                        Description:
                        %s
                                        
                        Review: %s
                        Rating: %s
                                        """, id, lockedTitle, name, author, pages, publishing,
                targetAge, watchedStatus, tagsList, description, review, score);

    }
}
