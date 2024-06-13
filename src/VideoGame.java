import java.util.ArrayList;

public class VideoGame extends Media {

    private int playtime;
    private ArrayList<String> devs;

    /**
     * Constructor for VideoGame.
     * @param id ID of game.
     * @param name Name of game.
     * @param review Review for the game.
     * @param rating Rating from 0-5 for the game.
     * @param targetAge The game's age rating.
     * @param isLocked Lock status for the game.
     * @param watchedStatus Watch status for the game.
     * @param description The game's description.
     * @param publishing Name of the company that licensed the game.
     * @param playtime The game's playtime.
     * @param devs Developers for the game.
     */
    public VideoGame(String id, String name, String review, int rating, int targetAge,
                     boolean isLocked, int watchedStatus, String description, String publishing,
                     int playtime, ArrayList<String> devs) {
        super(id, name, review, rating, targetAge, isLocked, watchedStatus, description, publishing);
        this.playtime = playtime;
        this.devs = devs;
    }

    /**
     * Constructor that doesn't require a review, rating, watched or lock status.
     * @param id ID of the game.
     * @param name Name of the game.
     * @param targetAge Game's age rating.
     * @param description Game's description.
     * @param publishing Name of the company that licensed the game.
     * @param playtime Game's playtime.
     * @param devs Developers for the game.
     */
    public VideoGame(String id, String name, int targetAge, String description, String publishing,
                     int playtime, ArrayList<String> devs) {
        super(id, name, targetAge, description, publishing);
        this.playtime = playtime;
        this.devs = devs;
    }

    /**
     * Print method for VideoGame objects.
     * @return String to be printed.
     */
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

    /**
     * Prints details regarding the game's fields.
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
        System.out.printf("║%-18s║%-18s║ Video Game ║%-14s║%-12s║%-12s║%-9s║\n", formatted_name, id, age, stars,
                getWatchedStatus(), isLocked);

    }

    /**
     * Getter for playtime.
     * @return playtime
     */
    public int getPlaytime() {
        return playtime;
    }

    /**
     * Getter for devs.
     * @return devs
     */
    public ArrayList<String> getDevs() {
        return devs;
    }
}
