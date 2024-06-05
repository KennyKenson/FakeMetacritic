import java.util.ArrayList;

public class Book extends Media {

    private int pages;
    private String author;
    private final String[] ALL_TAGS = {"include tags here"};

    public Book(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus, String description, String publishing, int pages, String author){
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.pages = pages;
        this.author = author;
    }

    public Book(String id, String name){
        super(id, name);
        this.pages = 0;
        this.author = "N/A";
    }

    public Book(){
        super();
        this.pages = 0;
        this.author = "N/A";
    }

    +getPages(): int
+getAuthor(): int
+toString(): String
+getTags(): ArrayList<String>
+allTags(): ArrayList<String




    @Override
    void addTag(String tag) {

    }

    @Override
    ArrayList<String> getTags() {
        return null;
    }

    @Override
    public String toString(){
        return "";
    }
}
