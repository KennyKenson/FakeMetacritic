import java.util.ArrayList;

public class Movie extends Media{

    private int duration;
    private ArrayList<String> producers;
    private String director;
    private ArrayList<String> starActors;
    private final String[] ALL_TAGS = {"help"};

    public Movie(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus, String description, String publishing, int pages, String author, int duration, ArrayList<String> producers, String director, ArrayList<String> starActors){
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;

    }

    public Movie(String id, String name){
        super(id, name);
    }

    public Movie(){
        super();
        this.duration = 0;
        this.producers = new ArrayList<>();
        this.director = "N/A";
        this.starActors = new ArrayList<>();
    }

    @Override
    void addTag(String tag) {

    }

    @Override
    ArrayList<String> getTags() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
