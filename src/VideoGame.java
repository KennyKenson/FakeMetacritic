import java.util.ArrayList;
import java.util.Arrays;

public class VideoGame extends Media {

    private int playtime;
    private ArrayList<String> devs;

    public VideoGame(String id, String name, String review, int rating, int targetAge,
                     boolean isLocked, int watchedStatus, String description, String publishing,
                     int playtime, ArrayList<String> devs) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.playtime = playtime;
        this.devs = devs;
    }

    public VideoGame(String id, String name, int age, String description, String publishing,
                     int playtime, ArrayList<String> devs) {
        super(id, name, age, description, publishing);
        this.playtime = playtime;
        this.devs = devs;
    }

    public VideoGame(String id, String name) {
        super(id, name);
        this.playtime = 0;
        this.devs = new ArrayList<>();
    }

    public VideoGame() {
        super();
        this.playtime = 0;
        this.devs = new ArrayList<>();
    }
    @Override
    public void addTags(ArrayList<String> tags) {
        for (String tag : tags) {
            this.addTag(tag);
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
        System.out.printf("║%-18s║%-18s║ Video Game ║%-14s║%-12s║%-12s║%-9s║\n", formatted_name, id, age, stars, getWatchedStatus(), isLocked);

    }

    public int getPlaytime() {
        return playtime;
    }

    public ArrayList<String> getDevs() {
        return devs;
    }
}
