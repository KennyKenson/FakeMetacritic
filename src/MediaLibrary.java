import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
                currentList.removeIf(media -> !(media.getTags().contains(KEYWORD)));
        }
    }

    public void filterMedia(int targetAge) {
        currentList.removeIf(media -> media.getRating() > targetAge);
    }

    public void filterRating(int rating) {
        currentList.removeIf(media -> media.getRating() < rating);
    }

    public void sortMediaByRating() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaByRating(0, currentList.size()-1);
        }
    }

    //Definitely need to check this method later. (and the other quicksort thing below)
    public void sortMediaByRating(int start, int end) {
        if (start < end) {
            Media pivot = currentList.get(start);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).getRating() >= pivot.getRating()) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).getRating() <= pivot.getRating()) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }

            currentList.set(left, pivot);

            sortMediaByRating(start, left-1);
            sortMediaByRating(right+1, end);
        }
    }

    public void sortMediaByName() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaByName(0, currentList.size()-1);
        }
    }

    public void sortMediaByName(int start, int end) {
        if (start < end) {
            Media pivot = currentList.get(start + (end-start)/2);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).getName().compareTo(pivot.getName()) >= 0) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).getName().compareTo(pivot.getName()) <= 0) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }

            currentList.set(left, pivot);

            sortMediaByRating(start, left-1);
            sortMediaByRating(right+1, end);
        }
    }

    public void sortMediaById() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaById(0, currentList.size()-1);
        }
    }

    public void sortMediaById(int start, int end) {
        if (start < end) {
            Media pivot = currentList.get(start + (end-start)/2);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).getId().compareTo(pivot.getId()) >= 0) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).getId().compareTo(pivot.getId()) <= 0) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }

            currentList.set(left, pivot);

            sortMediaByRating(start, left-1);
            sortMediaByRating(right+1, end);
        }
    }

    public void reverseList() {
        Collections.reverse(currentList);
    }

    public Media randomMedia() {
        Random random = new Random();
        int randomIndex = random.nextInt(currentList.size());
        return currentList.get(randomIndex);
    }

    public Media searchMedia(String id) {
        sortMediaById();
        int start = 0;
        int end = currentList.size()-1;
        int midpoint;

        while (start <= end) {
            midpoint = (start+end)/2;
            if (currentList.get(midpoint).getId().equals(id)) {
                return currentList.get(midpoint);
            } else if (currentList.get(midpoint).getId().compareTo(id) > 0) {
                start = midpoint+1;
            } else {
                end = midpoint-1;
            }
        }
        return null;
    }

    public Media findMedia(String id) {
        return searchMedia(id);
    }

    public void lockMedia(String id) {
        Media media = searchMedia(id);
        if (media.getIsLocked()) {
            media.lockMedia(false);
        } else {
            media.lockMedia(true);
        }
    }

    public void rateMedia(String id, int score) {
        Media media = searchMedia(id);
        if (score < 0) {
            return; //Won't change value if parameter is invalid.
        } else {
            media.changeRating(score);
        }
    }

    public void reviewMedia(String id, String review) {
        Media media = searchMedia(id);
        if (review.isEmpty()) {
            return; //Won't change value if parameter is invalid.
        } else {
            media.changeReview(review);
        }
    }

    public void removeRating(String id) {
        Media media = searchMedia(id);
        media.removeRating();
    }

    public void removeReview(String id) {
        Media media = searchMedia(id);
        media.removeReview();
    }

    public void setTargetAge(String id, String targetAge) {
        Media media = searchMedia(id);
        //media.changeTargetAge(targetAge); UNCOMMENT WHEN AADHYA ADDS THE METHOD.
    }

    public void resetCurrentList() {
        currentList.clear();
        currentList.addAll(mediaList);
    }

    public void removeMedia(String id) {
        Media media = searchMedia(id);
        mediaList.remove(media);
        currentList.remove(media);
    }

    public void addMedia(Media newMedia) {
        mediaList.add(newMedia);
        //Won't add the media to currentList immediately.
    }

    public void printBooks() {
        filterMedia("book");
        for (Media book : currentList) {
            System.out.println(book);
        }
    }

    public void printTvShows() {
        filterMedia("tvshow");
        for (Media show : currentList) {
            System.out.println(show);
        }
    }

    public void printMovies() {
        filterMedia("movie");
        for (Media movie : currentList) {
            System.out.println(movie);
        }
    }

    public void printVideoGames() {
        filterMedia("videogame");
        for (Media videogame : currentList) {
            System.out.println(videogame);
        }
    }

    public void printCurrentList() {
        for (Media media : currentList) {
            System.out.println(media);
        }
    }

    public boolean importMediaList(String fileName) {
        String identifier;
        String[] mediaInfo;
        String id;
        String name;
        int rating;
        String review;
        int targetAge;
        String description;
        String publishing;
        String tag; //This variable is looped multiple times to insert all tags into the imported Media object.
                    //Also located at the very end of the line.

        //For books
        int pages;
        String author;

        //For TV shows
        int seasons;
        int episodes;
        String[] producers; //Also for movies
        String director;  //Also for movies
        String[] starActors; //Also for movies

        //For video games
        int playtime;
        String[] devs;

        //For movies
        int duration;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                mediaInfo = line.split(",");
                id = mediaInfo[0];
                name = mediaInfo[1];
                rating = Integer.parseInt(mediaInfo[2]);
                review = mediaInfo[3];
                targetAge = Integer.parseInt(mediaInfo[4]);
                description = mediaInfo[5];
                publishing = mediaInfo[6];

                identifier = id.substring(0,1);

                switch(identifier) {
                    case "B":
                        pages = Integer.parseInt(mediaInfo[7]);
                        author = mediaInfo[8];
                        currentList.add(new Book(id, name, review, rating, targetAge,
                                false, 0, description, publishing, pages, author));
                        break;
                    case "T":
                        seasons = Integer.parseInt(mediaInfo[7]);
                        episodes = Integer.parseInt(mediaInfo[8]);
                        producers = mediaInfo[9].split(";");
                        director = mediaInfo[10];
                        starActors = mediaInfo[11].split(";");
                        currentList.add(new TvShow(id, name, review, rating, targetAge,
                                false, 0, description, publishing,
                                seasons, episodes,producers, director, starActors));
                        break;
                    case "M":
                        duration = Integer.parseInt(mediaInfo[7]);
                        producers = mediaInfo[8].split(";");
                        director = mediaInfo[9];
                        starActors = mediaInfo[10].split(";");
                        currentList.add(new Movie(id, name, review, rating, targetAge,
                                false, 0, description, publishing, duration, producers,
                                director, starActors));
                        break;
                    case "V":
                }
            }
            return true;
        } catch (IOException iox) {
            System.out.println("Failed to import file.");
            return false;
        }
    }
}
