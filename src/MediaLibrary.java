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
        keyword = keyword.toUpperCase();
        if (keyword.equals("MOVIE") || keyword.equals("TVSHOW") || keyword.equals("BOOK") || keyword.equals("VIDEOGAME")) {
            switch(keyword) {
                case "MOVIE":
                    for (Media media : currentList) {
                        if (!(media instanceof Movie)) {
                        }
                    }
                    break;
                case "TVSHOW":
                    break;
                case "BOOK":
                    break;
                case "VIDEOGAME":
                    break;
            }
        }
    }
}
