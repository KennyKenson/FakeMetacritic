import java.util.ArrayList;

public class TvShow extends Media {

    int seasons;
    int episodes;
    String[] producers;
    String director;
    String[] starActors;

    public TvShow(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus,
                String description, String publishing, int seasons, int episodes, String[] producers, String director, String[] starActors) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.seasons = seasons;
        this.episodes = episodes;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;
    }
    public void addTag(String tag) {
    }

    public ArrayList<String> getTags() {
        return null;
    }

    @Override
    public void addTags(ArrayList<String> tags) {

    }

    @Override
    public String toString() {
        return "";
    }
}
