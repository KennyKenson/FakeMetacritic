import java.util.ArrayList;
import java.util.Arrays;

public class VideoGame extends Media {

    int playtime;
    String[] devs;

    public VideoGame(String id, String name, String review, int rating, int targetAge,
                     boolean isLocked, int watchedStatus, String description, String publishing,
                     int playtime, String[] devs) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.playtime = playtime;
        this.devs = devs;
    }

    private int playtime;
    private ArrayList<String> devs;
    private final String[] ALL_TAGS = {"help"};
    public VideoGame(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus,
                     String description, String publishing, int pages, String author, String[] allTags) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        ALL_TAGS = allTags;
        this.pages = pages;
        this.author = author;
    }

    public VideoGame(String id, String name) {
        super(id, name);
        this.pages = 0;
        this.author = "N/A";
    }

    public VideoGame() {
        super();
        this.pages = 0;
        this.author = "N/A";
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

    public int getPlaytime() {
        return playtime;
    }

    public String[] getDevs() {
        return devs;
    }
}
