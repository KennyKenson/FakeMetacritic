import java.util.ArrayList;
public class MediaLibrary {
    ArrayList<Media> mediaList;
    ArrayList<Media> currentList;
    String DEFAULT_FILE = "media";

    public MediaLibrary() {
        mediaList = new ArrayList<>();
        currentList = new ArrayList<>();
    }

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }

    public void filterMedia(String keyword) {

    }
}
