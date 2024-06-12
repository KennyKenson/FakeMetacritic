import java.util.ArrayList;

public class Movie extends Media {

    private int duration;
    private ArrayList<String> producers;
    private String director;
    private ArrayList<String> starActors;

    /**
     * Constructor method.
     *
     * @param id ID of the movie.
     * @param name Name of the movie.
     * @param review A review for the movie.
     * @param rating A rating for the movie from 0-5.
     * @param targetAge The age rating for the movie.
     * @param isLocked Status to determine if the media is editable or not.
     * @param watchedStatus Status to determine what user plan's to do with the media.
     * @param description A description for the movie.
     * @param publishing The name of the company that licensed the media.
     * @param duration The duration of the movie.
     * @param producers The producers for the movie.
     * @param director The movie's director.
     * @param starActors Notable actors in the movie.
     */
    public Movie(String id, String name, String review, int rating, int targetAge,
                 boolean isLocked, int watchedStatus, String description, String publishing,
                 int duration, ArrayList<String> producers, String director, ArrayList<String> starActors) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;

    }

    /**
     * Constructor that doesn't require a review, rating, watched or lock status.
     *
     * @param id The movie's ID.
     * @param name The movie's name.
     * @param age The movie's target age.
     * @param description Description for the movie.
     * @param publishing Name of the company that licensed the movie.
     * @param duration Duration of the movie.
     * @param producers Producers for the movie.
     * @param director The movie's director.
     * @param starActors Notable actors in the movie.
     */
    public Movie(String id, String name, int age, String description, String publishing, int duration,
                 ArrayList<String> producers, String director, ArrayList<String> starActors) {
        super(id, name, age, description, publishing);
        this.duration = duration;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;
    }

    /**
     * Constructor that only requires a name and ID.
     *
     * @param id Movie's ID.
     * @param name Name of the movie.
     */
    public Movie(String id, String name) {
        super(id, name);
        this.duration = 0;
        this.producers = new ArrayList<>();
        producers.add("N/A");
        this.director = "N/A";
        this.starActors = new ArrayList<>();
        starActors.add("N/A");

    }

    /**
     * Getter for duration.
     *
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Getter for producers.
     *
     * @return producers
     */
    public ArrayList<String> getProducers() {
        return producers;
    }

    /**
     * Getter for director.
     *
     * @return director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Getter for starActors.
     *
     * @return starActors
     */
    public ArrayList<String> getStarActors() {
        return starActors;
    }

    /**
     * toString method for Movie.
     *
     * @return The printed value for a Movie object.
     */
    @Override
    public String toString() {
        String lockedTitle = "";
        String tagsList = "";
        String producersList = "";
        String actorsList = "";

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

        if (!producers.isEmpty()) {
            producersList = producers.get(0);
            for (int i = 1; i < producers.size(); i++) {
                tagsList += ", " + producers.get(i);
            }
        }

        if (!starActors.isEmpty()) {
            actorsList = starActors.get(0);
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
                                        """, id, lockedTitle, name, director, producersList, duration, actorsList,
                publishing, targetAge, watchedStatus, tagsList, description, review, score);
    }

    /**
     * Prints all the details regarding the movie.
     */
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
        System.out.printf("║%-18s║%-18s║   Movie    ║%-14s║%-12s║%-12s║%-9s║\n", formatted_name, id, age, stars,
                getWatchedStatus(), isLocked);

    }
}
