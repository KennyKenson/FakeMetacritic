import java.util.ArrayList;
import java.util.Arrays;

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
        }

    }

    @Override
    public String toString() {
        return "";
    }

    public int getSeasons() {
        return seasons;
    }

    public int getEpisodes() {
        return episodes;
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
}
