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
        switch(keyword) {
            case "MOVIE":
                currentList.removeIf(media -> !(media instanceof Movie));
                return;
            case "TVSHOW":
                currentList.removeIf(media -> !(media instanceof TvShow));
                return;
            case "BOOK":
                currentList.removeIf(media -> !(media instanceof Book));
                return;
            case "VIDEOGAME":
                currentList.removeIf(media -> !(media instanceof VideoGame));
                return;
            default:
                final String KEYWORD = keyword; //Needed to make 'keyword' a constant or the removeIf() function  below screams at me.
                currentList.removeIf(media -> !(media.tags.contains(KEYWORD)));
        }
    }

    public void filterMedia(int targetAge) {
        currentList.removeIf(media -> media.targetAge > targetAge);
    }

    public void filterRating(int rating) {
        currentList.removeIf(media -> media.rating < rating);
    }

    public void sortMediaByRating() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaByRating(0, currentList.size()-1);
        }
    }

    public void sortMediaByRating(int start, int end) {
        if (end-start > 1) {
            Media pivot = currentList.get(start + (end-start)/2);
            Media placeholder;
            int left = 0;
            int right = end-1;

            //Switches last index with pivot index.
            currentList.set(start+(end-start)/2, currentList.get(end));
            currentList.set(end, pivot);

            while(left <= right && left <= end && right >= start) {

                while (currentList.get(left).rating <= pivot.rating) {
                    left++;
                }

                while (currentList.get(right).rating >= pivot.rating) {
                    right--;
                }

                if (left < right) {
                    placeholder = currentList.get(left);
                    currentList.set(left, currentList.get(right));
                    currentList.set(right, placeholder);
                }
            }
            currentList.set(end, currentList.get(left));
            currentList.set(left, pivot);

            sortMediaByRating(start, right);
            sortMediaByRating(left+1, end);
        }
    }
}
