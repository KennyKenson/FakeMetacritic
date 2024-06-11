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

    /**
     * Constructor for Media.
     * @param id Media's ID.
     * @param name Name of media.
     * @param review A review for the media.
     * @param rating A rating from 0-5 for the media.
     * @param targetAge The media's age rating.
     * @param isLocked Media's lock status.
     * @param watchedStatus Media's watched status.
     * @param description Description for the media.
     * @param publishing Name of the company that licensed the media.
     */
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
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor that doesn't require a review, rating, watched or lock status.
     * @param id
     * @param name
     * @param targetAge
     * @param description
     * @param publishing
     */
    public Media(String id, String name, int targetAge, String description, String publishing) {
        this.id = id;
        this.name = name;
        this.targetAge = targetAge;
        this.description = description;
        this.publishing = publishing;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor that only requires a name and ID.
     * @param id
     * @param name
     */
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
        this.tags = new ArrayList<>();
    }

    /**
     * Constructor that requires no parameters.
     */
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
        this.tags = new ArrayList<>();
    }

    /**
     * Getter for id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for review.
     * @return review
     */
    public String getReview() {
        return review;
    }

    /**
     * Getter for rating.
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Getter for targetAge.
     * @return targetAge
     */
    public int getTargetAge() {
        return targetAge;
    }

    /**
     * Getter for isLocked.
     * @return isLocked
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Getter for watchedStatus.
     * @return watchedStatus
     */
    public String getWatchedStatus() {
        return WATCHED_STATUS_STRINGS[watchedStatus];
    }

    /**
     * Getter for description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for publishing.
     * @return publishing
     */
    public String getPublishing() {
        return publishing;
    }

    /**
     * Removes the review on a piece of media.
     */
    public void removeReview() {
        if (!isLocked) {
            review = "N/A";
        } else {
            System.out.println("The selected media is currently locked. Unlock in order to modify anything. ");
        }
    }

    /**
     * Removes the rating on a piece of media.
     */
    public void removeRating() {
        rating = -1;
    }

    /**
     * Setter for review.
     * @param review The new review.
     */
    public void changeReview(String review) {
        this.review = review;
    }

    /**
     * Setter for rating.
     * @param score The new rating.
     */
    public void changeRating(int score) {
        this.rating = score;
    }

    /**
     * Setter for watchedStatus.
     * @param n The new watchedStatus.
     */
    public void changeWatchedStatus(int n) {
        if (n > 0 && n < 5) {
            watchedStatus = n;
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Setter for isLocked.
     * @param lock The new locked status.
     */
    public void lockMedia(boolean lock) {
        isLocked = lock;
    }

    /**
     * Getter for tags.
     * @return tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Abstract class for adding news tags to the 'tags' field.
     * @param tags List of new tags to be added.
     */
    public abstract void addTags(ArrayList<String> tags);

    /**
     * Adds a single new tag to the 'tags' field.
     * @param tag The new tag.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * toString() method for Media objects.
     * @return Printed value of a Media object.
     */
    @Override
    public abstract String toString();

    /**
     * Prints details of a Media object regarding all of its fields.
     */
    public abstract void printDetails();
}
