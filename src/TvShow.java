import java.util.ArrayList;

public class TvShow extends Media {

    int seasons;
    int episodes;
    ArrayList<String> producers;
    String creator;
    ArrayList<String> starActors;

    /**
     * Constructor for TvShow.
     * @param id ID of the show.
     * @param name Name of the show.
     * @param review A review for the show.
     * @param rating Rating from 0-5 for the show.
     * @param targetAge The show's age rating.
     * @param isLocked The show's lock status.
     * @param watchedStatus The show's watch status.
     * @param description The show's description.
     * @param publishing Name of the company that licensed the show.
     * @param seasons Number of seasons the show has.
     * @param episodes Number of episodes the show has.
     * @param producers Producers for the show.
     * @param creator The show's creator.
     * @param starActors Notable actors in the show.
     */
    public TvShow(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus,
                  String description, String publishing, int seasons, int episodes, ArrayList<String> producers,
                  String creator, ArrayList<String> starActors) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.seasons = seasons;
        this.episodes = episodes;
        this.producers = producers;
        this.creator = creator;
        this.starActors = starActors;
    }

    /**
     * Constructor that doesn't require a review, rating, watched or lock status.
     * @param id ID of the show.
     * @param name Name of the show.
     * @param targetAge Age rating of the show.
     * @param description Description for the show.
     * @param publishing Name of the company that licensed the show.
     * @param seasons Number of seasons the show has.
     * @param episodes Number of episodes the show has.
     * @param producers Show's producers.
     * @param creator Show's creator.
     * @param starActors Notable actors in the show.
     */
    public TvShow(String id, String name, int targetAge, String description, String publishing, int seasons, int episodes,
                  ArrayList<String> producers, String creator, ArrayList<String> starActors) {
        super(id, name, targetAge, description, publishing);
        this.seasons = seasons;
        this.episodes = episodes;
        this.producers = producers;
        this.creator = creator;
        this.starActors = starActors;
    }

    /**
     * Constructor that requires only name and ID.
     * @param id ID of the show.
     * @param name Name of the show.
     */
    public TvShow(String id, String name) {
        super(id, name);
        this.seasons = 0;
        this.episodes = 0;
        this.producers = new ArrayList<>();
        producers.add("N/A");
        this.creator = "N/A";
        this.starActors = new ArrayList<>();
        starActors.add("N/A");
    }

    /**
     * Print method for TvShow objects.
     * @return String to be printed.
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
        if (tags != null && !tags.isEmpty()) {
            tagsList = tags.get(0);
            for (int i = 1; i < tags.size(); i++) {
                tagsList += ", " + tags.get(i);
            }
        }

        if (producers != null && !producers.isEmpty()) {
            producersList = producers.get(0);
            for (int i = 1; i < producers.size(); i++) {
                tagsList += ", " + producers.get(i);
            }
        }

        if (starActors != null && !starActors.isEmpty()) {
            actorsList = starActors.get(0);
            for (int i = 1; i < starActors.size(); i++) {
                actorsList += ", " + starActors.get(i);
            }
        }

        if (rating == -1){
            score = "No rating";
        } else {
            for (int i = 0; i < rating; i++) {
                score += "★";
            }

            for (int i = 0; i < 5 - rating; i++) {
                score += "☆";
            }
        }

        return String.format("""

                        TV Show Viewer: %s
                        %s
                        Name: %s
                        Director: %s
                        Producer(s): %s
                        Seasons: %s
                        Episodes: %s
                        Star Actors: %s
                        Studio: %s
                        Target Age: %s+
                        Status: %s
                        Tags: %s
                                        
                        Description:
                        %s
                                        
                        Review: %s
                        Rating: %s
                                        """, id, lockedTitle, name, creator, producersList, seasons, episodes,
                actorsList, publishing, targetAge, watchedStatus, tagsList, description, review, score);
    }

    /**
     * Prints details regarding the object's fields.
     */
    @Override
    public void printDetails() {
        String stars = "   ";
        if (this.rating == -1) {
            stars = "No rating";
        } else if (this.rating == 0) {
            stars = "   ";
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

        System.out.printf("║%-18s║%-18s║  TV Show   ║%-14s║%-12s║%-12s║%-9s║\n",
                formatted_name, id, age, stars, getWatchedStatus(), isLocked);

    }

    /**
     * Getter for seasons.
     * @return seasons
     */
    public int getSeasons() {
        return seasons;
    }

    /**
     * Getter for episodes.
     * @return episodes
     */
    public int getEpisodes() {
        return episodes;
    }

    /**
     * Getter for producers.
     * @return producers
     */
    public ArrayList<String> getProducers() {
        return producers;
    }

    /**
     * Getter for director.
     * @return director.
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Getter for starActors.
     * @return starActors
     */
    public ArrayList<String> getStarActors() {
        return starActors;
    }
}
