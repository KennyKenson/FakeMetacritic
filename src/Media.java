import java.util.ArrayList;

abstract class Media {
    protected String id;
    protected String name;
    protected String review;
    protected int rating;
    protected int targetAge;
    protected boolean isLocked;
    protected static final String[] WATCHED_STATUS_STRINGS = {"Not Watched", "Planning to Watch", "Currently Watching",
            "Completed", "Dropped"};
    protected int watchedStatus;
    protected String description;
    protected String publishing;
    protected ArrayList<String> tags;

    public Media(String id, String name, String review, int rating, int targetAge, boolean isLocked, int watchedStatus,
                 String description, String publishing) {
        this.id = id;
        this.name = name;
        this.review = review;
        this.rating = rating;
        this.targetAge = targetAge;
        this.isLocked = isLocked;
        this.watchedStatus = watchedStatus;
        this.description = description;
        this.publishing = publishing;
        this.tags = new ArrayList<String>();
    }

    public Media(String id, String name) {
        this.id = id;
        this.name = name;
        this.review = "N/A";
        this.rating = -1;
        this.targetAge = 0;
        this.isLocked = false;
        this.watchedStatus = 0;
        this.description = "N/A";
        this.publishing = "N/A";
        this.tags = new ArrayList<String>();
    }

    public Media() {
        this.id = "000000";
        this.name = "N/A";
        this.review = "N/A";
        this.rating = -1;
        this.targetAge = 0;
        this.isLocked = false;
        this.watchedStatus = 0;
        this.description = "N/A";
        this.publishing = "N/A";
        this.tags = new ArrayList<String>();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }

    public int getTargetAge() {
        return targetAge;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public String getWatchedStatus() {
        return WATCHED_STATUS_STRINGS[watchedStatus];
    }

    public String getDescription() {
        return description;
    }

    public String getPublishing() {
        return publishing;
    }

    public void removeReview() {
        if (!isLocked) {
            review = "N/A";
        } else {
            System.out.println("The selected media is currently locked. Unlock in order to modify anything. ");
        }
    }

    public void removeRating() {
        rating = -1;
    }

    public void changeReview(String review) {

        // this set of code changes commas (U+002C) into a single low-9 quotation mark (U+201A) (‚) so that the csv does
        // not interpret the comma as a new column.
        String review_diff_commas = "";
        for (int i = 0; i < review.length(); i++) {
            char c = review.charAt(i);
            if (review.charAt(i) == ',') { // this is U+002C
                c = '‚'; // this is U+201A
            }
            review_diff_commas += c;
        }

        this.review = review_diff_commas;
    }

    public void changeRating(int score) {
        this.rating = score;
    }

    public void changeWatchedStatus(int n) {
        if (n > 0 && n < 5) {
            watchedStatus = n;
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void lockMedia(boolean lock) {
        isLocked = lock;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public abstract void addTags(ArrayList<String> tags);

    public abstract void addTag(String tag);

    @Override
    public abstract String toString();
}
