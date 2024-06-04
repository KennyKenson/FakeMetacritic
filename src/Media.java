import java.util.ArrayList;

abstract class Media {
    protected String id;
    protected String name;
    protected String review;
    protected int rating;
    protected int targetAge;
    protected boolean isLocked;
    protected static final String[] WATCHED_STATUS_STRINGS = {"Not Watched", "Planning to Watch", "Currently Watching", "Completed", "Dropped"};
    protected int watchedStatus;
    protected String description;
    protected String publishing;
    protected ArrayList<String> tags;


    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getReview(){
        return review;
    }
    public int getRating(){
        return rating;
    }
    public int getTargetAge(){
        return targetAge;
    }
    public boolean getIsLocked(){
        return isLocked;
    }
    public String getWatchedStatus(){
        return WATCHED_STATUS_STRINGS[watchedStatus];
    }
    public String getDescription(){
        return description;
    }
    public String getPublishing(){
        return publishing;
    }
    public void removeReview(){
        if (!isLocked) {
            review = "";
        } else {
            System.out.println("The selected media is currently locked. Unlock in order to modify anything. ");
        }
    }
    public void removeRating(){
        rating = -1;
    }
    public void changeReview(String review){
        this.review = review;
    }
    public void changeRating(int score){
        this.rating = score;
    }
    public void changeWatchedStatus(int n){
        if (n > 0 && n < 5){
            watchedStatus = n;
        } else {
            System.out.println("Invalid choice.");
        }
    }
    public void lockMedia(boolean lock){
        isLocked = lock;
    }
    abstract void addTag(String tag);
    abstract ArrayList<String> getTags();
    @Override
    abstract String toString();
}
