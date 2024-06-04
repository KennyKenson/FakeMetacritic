import java.util.ArrayList;
import java.util.Collection;
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
                    currentList.removeIf(media -> !(media instanceof Movie));
                    break;
                case "TVSHOW":
                    currentList.removeIf(media -> !(media instanceof TvShow));
                    break;
                case "BOOK":
                    currentList.removeIf(media -> !(media instanceof Book));
                    break;
                case "VIDEOGAME":
                    currentList.removeIf(media -> !(media instanceof VideoGame));
                    break;
            }
        }
    }
}
