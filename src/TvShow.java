import java.util.ArrayList;
import java.util.Arrays;

public class TvShow extends Media {

    int seasons;
    int episodes;
    ArrayList<String> producers;
    String director;
    ArrayList<String> starActors;
    private static final String[] ALL_TAGS = {"help"};

    public TvShow(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus,
                String description, String publishing, int seasons, int episodes, ArrayList<String> producers, String director,
                  ArrayList<String> starActors) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.seasons = seasons;
        this.episodes = episodes;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;
    }

    public TvShow(String id, String name, int age, String description, String publishing, int seasons, int episodes,
                  ArrayList<String> producers, String director, ArrayList<String> starActors) {
        super(id, name, age, description, publishing);
        this.seasons = seasons;
        this.episodes = episodes;
        this.producers = producers;
        this.director = director;
        this.starActors = starActors;
    }

    public TvShow(String id, String name) {
        super(id, name);
        this.seasons = 0;
        this.episodes = 0;
        this.producers = new ArrayList<>();
        this.director = "N/A";
        this.starActors = new ArrayList<>();
    }

    public TvShow() {
        super();
        this.seasons = 0;
        this.episodes = 0;
        this.producers = new ArrayList<>();
        this.director = "N/A";
        this.starActors = new ArrayList<>();
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

    @Override
    public void printDetails() {
        String stars = "   ";
        if (rating == -1){
            stars = "No rating";
        } else {
            String rate = "";
            for (int i = 0; i < rating; i++) {
                stars += "*";
            }
        }
        String age = "";
        if (targetAge == 0){
            age = "No age given";
        } else {
            age = Integer.toString(targetAge);
        }

        String formatted_name = "";

        if (name.length() > 18){
            formatted_name = name.substring(0, 15) + "...";
        } else {
            formatted_name = name;
        }

        System.out.printf("║%-18s║%-18s║  TV Show   ║%-14s║%-12s║%-12s║%-9s║\n", formatted_name, id, age, stars, getWatchedStatus(), isLocked);

    }

    public int getSeasons() {
        return seasons;
    }

    public int getEpisodes() {
        return episodes;
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
}
