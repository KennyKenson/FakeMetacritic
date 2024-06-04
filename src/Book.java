import java.util.ArrayList;

public class Book extends Media {

    public Book(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus, String description, String publishing){
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
    }



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
