import java.util.ArrayList;
import java.util.Arrays;

public class Book extends Media {

    private int pages;
    private String author;
    private final String[] ALL_TAGS = {"include tags here"};

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
    public void addTag(String tag) {
        if (Arrays.binarySearch(ALL_TAGS, tag) != -1) {
            tags.add(tag);
        } // if not, can we still add tag?

    }

    @Override
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            this.addTag(tag);
        }
    }

    @Override
    public void printDetails(){
        String stars = "";
        if (rating == -1){
            stars = "No rating";
        }
        String age = "";
        if (targetAge == 0){
            age = "No age given";
        }
        System.out.printf("║%-18s║%-18s║    Book    ║%-14s║%-12s║%-12s║%-9s║\n", name, id, age, stars, getWatchedStatus(), isLocked);
    }

    @Override
    public String toString() {
        return "";

    }
}
