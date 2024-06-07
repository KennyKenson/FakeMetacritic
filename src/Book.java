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
        }

    }

    @Override
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            this.addTag(tag);
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
