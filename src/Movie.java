import java.util.ArrayList;
import java.util.Arrays;

public class Movie extends Media {

    private int duration;
    private String[] producers;
    private String director;
    private String[] starActors;
    private final String[] ALL_TAGS = {"help"};

    public Movie(String id, String name, String review, int rating, int targetAge,
                 boolean isLocked, int watchedStatus, String description, String publishing,
                 int duration, String[] producers, String director, String[] starActors) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;

    }

    public Movie(String id, String name, int age, String description, String publishing, int duration,
                 String[] producers, String director, String[] starActors) {
        super(id, name, age, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;
    }

    public Movie(String id, String name) {
        super(id, name);
        this.duration = 0;
        this.producers = null;
        this.director = "N/A";
        this.starActors = null;
    }

    public Movie() {
        super();
        this.duration = 0;
        this.producers = null;
        this.director = "N/A";
        this.starActors = null;
    }

    public int getDuration() {
        return duration;
    }

    public String[] getProducers() {
        return producers;
    }

    public String getDirector() {
        return director;
    }

    public String[] getStarActors() {
        return starActors;
    }

    @Override
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            this.addTag(tag);
        }

    }

    @Override
    public void addTag(String tag) {
        if (Arrays.binarySearch(ALL_TAGS, tag) != -1) {
            tags.add(tag);
        } // if not, can we still add tag?

    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void printDetails() {
        String stars = "";
        if (rating == -1){
            stars = "No rating";
        }
        String age = "";
        if (targetAge == 0){
            age = "No age given";
        }
        System.out.printf("║%-18s║%-18s║   Movie    ║%-14s║%-12s║%-12s║%-9s║\n", name, id, age, stars, getWatchedStatus(), isLocked);

    }
}
