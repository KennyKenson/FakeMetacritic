import java.util.ArrayList;

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
        devs.add("N/A");
    }

    @Override
    public String toString() {
        String lockedTitle = "";
        String tagsList = "";
        String devsList = "";

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

        if (!devs.isEmpty()) {
            devsList = devs.get(0);
            for (int i = 1; i < devs.size(); i++) {
                tagsList += ", " + devs.get(i);
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
                        Developers: %s
                        Gameplay Duration: %s
                        Studio: %s
                        Target Age: %s+
                        Status: %s
                        Tags: %s
                                        
                        Description:
                        %s
                                        
                        Review: %s
                        Rating: %s
                                        """, id, lockedTitle, name, devsList, playtime, publishing,
                targetAge, watchedStatus, tagsList, description, review, score);
    }

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
        System.out.printf("║%-18s║%-18s║ Video Game ║%-14s║%-12s║%-12s║%-9s║\n", formatted_name, id, age, stars,
                getWatchedStatus(), isLocked);

    }

    public int getPlaytime() {
        return playtime;
    }

    public ArrayList<String> getDevs() {
        return devs;
    }
}
