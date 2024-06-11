import java.util.ArrayList;

public class Movie extends Media {

    private int duration;
    private ArrayList<String> producers;
    private String director;
    private ArrayList<String>  starActors;

    public Movie(String id, String name, String review, int rating, int targetAge,
                 boolean isLocked, int watchedStatus, String description, String publishing,
                 int duration, ArrayList<String> producers, String director, ArrayList<String> starActors) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;

    }

    public Movie(String id, String name, int age, String description, String publishing, int duration,
                 ArrayList<String> producers, String director, ArrayList<String> starActors) {
        super(id, name, age, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;
    }

    public Movie(String id, String name) {
        super(id, name);
        this.duration = 0;
        this.producers = new ArrayList<>();
        this.director = "N/A";
        this.starActors = new ArrayList<>();
    }

    public Movie() {
        super();
        this.duration = 0;
        this.producers = new ArrayList<>();
        this.director = "N/A";
        this.starActors = new ArrayList<>();
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<String> getProducers() {
        return producers;
    }

    public String getDirector() {
        return director;
    }

    public ArrayList<String> getStarActors() {
        return starActors;
    }

    @Override
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            this.addTag(tag);
        }

    }

    @Override
    public String toString() {
        String lockedTitle = "";
        String tagsList = "";
        String producersList = "";
        String actorsList = "";

        String score = "";
        if (isLocked){
            lockedTitle = "------------------[LOCKED TITLE]------------------";
        }
        if (!tags.isEmpty()) {
            tagsList = tags.getFirst();
            for (int i = 1; i < tags.size(); i++) {
                tagsList += ", " + tags.get(i);
            }
        }

        if (!producers.isEmpty()) {
            producersList = producers.getFirst();
            for (int i = 1; i < producers.size(); i++) {
                tagsList += ", " + producers.get(i);
            }
        }

        if (!starActors.isEmpty()) {
            actorsList = starActors.getFirst();
            for (int i = 1; i < starActors.size(); i++) {
                actorsList += ", " + starActors.get(i);
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

TV Show Viewer: %s
%s
Name: %s
Director: %s
Producer(s): %s
Duration: %s min
Star Actors: %s
Studio: %s
Target Age: %s+
Status: %s
Tags: %s
                
Description:
%s
                
Review: %s
Rating: %s
                """, id, lockedTitle, name, director, producersList, duration, actorsList, publishing,
                targetAge, watchedStatus, tagsList, description, review, score);
    }

    @Override
    public void printDetails() {
        String stars = "   ";
        if (rating == -1){
            stars = "No rating";
        } else {
            for (int i = 0; i < rating; i++) {
                stars += "*";
            }
        }
        String age;
        if (targetAge == 0){
            age = "No age given";
        } else {
            age = Integer.toString(targetAge);
        }

        String formatted_name;

        if (name.length() > 18){
            formatted_name = name.substring(0, 15) + "...";
        } else {
            formatted_name = name;
        }
        System.out.printf("║%-18s║%-18s║   Movie    ║%-14s║%-12s║%-12s║%-9s║\n", formatted_name, id, age, stars, getWatchedStatus(), isLocked);

    }
}
