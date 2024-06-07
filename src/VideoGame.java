import java.util.ArrayList;

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

    public int getPlaytime() {
        return playtime;
    }

    public String[] getDevs() {
        return devs;
    }
}
