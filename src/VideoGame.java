import java.util.ArrayList;
import java.util.Arrays;

public class VideoGame extends Media {

    private int playtime;
    private String[] devs;
    private final String[] ALL_TAGS = {"help"};

    public VideoGame(String id, String name, String review, int rating, int targetAge,
                     boolean isLocked, int watchedStatus, String description, String publishing,
                     int playtime, String[] devs) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.playtime = playtime;
        this.devs = devs;
    }



    public VideoGame(String id, String name) {
        super(id, name);
        this.playtime = 0;
        this.devs = null;
    }

    public VideoGame() {
        super();
        this.playtime = 0;
        this.devs = null;
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
