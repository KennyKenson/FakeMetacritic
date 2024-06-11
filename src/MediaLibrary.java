import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MediaLibrary {

    private ArrayList<Media> mediaList;
    private ArrayList<Media> currentList;
    String DEFAULT_FILE = "media";

    /**
     * Constructor for MediaLibrary.
     */
    public MediaLibrary() {
        mediaList = new ArrayList<>();
        currentList = new ArrayList<>();
    }

    /**
     * Getter for mediaList.
     * @return mediaList
     */
    public ArrayList<Media> getMediaList() {
        return mediaList;
    }

    /**
     * Getter for currentList.
     * @return currentList
     */
    public ArrayList<Media> getCurrentList() {
        return currentList;
    }

    /**
     * Removes all Media in currentList that don't fit the criteria of the given keyword.
     * Keyword is either a subclass (ie. Book, Movie, etc.) or a tag.
     * @param keyword The keyword used to filter the list.
     */
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

    /**
     * Removes media that is above the target age rating.
     * @param targetAge The target age rating.
     */
    public void filterMedia(int targetAge) {
        currentList.removeIf(media -> media.getRating() > targetAge);
    }

    /**
     * Removes media that is below the target rating.
     * @param rating The target rating.
     */
    public void filterRating(int rating) {
        currentList.removeIf(media -> media.getRating() < rating);
    }

    /**
     * Starting method for the recursive method sortMediaByRating().
     */
    public void sortMediaByRating() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaByRating(0, currentList.size()-1);
        }
    }

    /**
     * Sorts media from highest to lowest rating.
     * @param start The starting index of the section sorted.
     * @param end The final index of the section sorted.
     */
    public void sortMediaByRating(int start, int end) {
        if (start < end) {
            Media pivot = currentList.get(start);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).getRating() >= pivot.getRating() && left < right) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).getRating() <= pivot.getRating() && left < right) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }
            currentList.set(left, pivot);

            sortMediaByRating(start, left-1);
            sortMediaByRating(right+1, end);
        }
    }

    /**
     * Starting method for recursive method sortMediaByName.
     */
    public void sortMediaByName() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaByName(0, currentList.size()-1);
        }
    }

    /**
     * Sorts media alphabetically by their names.
     * @param start The starting index of the section sorted.
     * @param end The final index of the section sorted.
     */
    public void sortMediaByName(int start, int end) {
        if (start < end) {
            Media pivot = currentList.get(start + (end-start)/2);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).getName().compareToIgnoreCase(pivot.getName()) <= 0 && left < right) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).getName().compareToIgnoreCase(pivot.getName()) >= 0 && left < right) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }

            currentList.set(left, pivot);

            sortMediaByName(start, left-1);
            sortMediaByName(right+1, end);
        }
    }

    /**
     * Starting method for the recursive method sortMediaById().
     */
    public void sortMediaById() {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaById(0, currentList.size()-1);
        }
    }

    /**
     * Sorts media alphabetically using their IDs.
     * @param start The starting index of the section sorted.
     * @param end The final index of the section sorted.
     */
    public void sortMediaById(int start, int end) {
        if (start < end) {
            Media pivot = currentList.get(start + (end-start)/2);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).getId().compareTo(pivot.getId()) >= 0 && left < right) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).getId().compareTo(pivot.getId()) <= 0 && left < right) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }

            currentList.set(left, pivot);

            sortMediaById(start, left-1);
            sortMediaById(right+1, end);
        }
    }

    /**
     * Flips currentList around.
     */
    public void reverseList() {
        Collections.reverse(currentList);
    }

    /**
     * Returns A random piece of media from currentList.
     * @return Random media.
     */
    public Media randomMedia() {
        Random random = new Random();
        int randomIndex = random.nextInt(currentList.size());
        return currentList.get(randomIndex);
    }

    /**
     * Searchs for a piece of media using its ID.
     * @param id ID of media.
     * @return The desired media.
     */
    public Media searchMedia(String id) {
        for (Media media : mediaList){
            if (media.getId().equals(id)){
                return media;
            }
        }
        return null;
    }

    /**
     * Searchs for a piece of media using its name.
     * @param name Name of media.
     * @return The desired media's ID.
     */
    public String findMedia(String name) {
        for (Media media : currentList) {
            if (name.equalsIgnoreCase(media.getName())) {
                return media.getId();
            }
        }
        return null;
    }

    /**
     * Locks/unlocks a piece of media using its ID. (Depends on its previous status.)
     * @param id ID of media.
     */
    public void lockMedia(String id) {
        Media media = searchMedia(id);
        if (media.getIsLocked()) {
            media.lockMedia(false);
        } else {
            media.lockMedia(true);
        }
    }

    /**
     * Rates a piece of media using its ID.
     * @param id ID of media.
     * @param score New score for media.
     */
    public void rateMedia(String id, int score) {
        Media media = searchMedia(id);
        if (score < 0) {
            return; //Won't change value if parameter is invalid.
        } else {
            media.changeRating(score);
        }
    }

    /**
     * Reviews a piece of media based on its ID.
     * @param id ID of media.
     * @param review The new review for the media.
     */
    public void reviewMedia(String id, String review) {
        Media media = searchMedia(id);
        if (review.isEmpty()) {
            return; //Won't change value if parameter is invalid.
        } else {
            media.changeReview(review);
        }
    }

    /**
     * Removes the rating from a piece of media using its ID.
     * @param id ID of media.
     */
    public void removeRating(String id) {
        Media media = searchMedia(id);
        media.removeRating();
    }

    /**
     * Removes the review from a piece of media using its ID.
     * @param id ID of media.
     */
    public void removeReview(String id) {
        Media media = searchMedia(id);
        media.removeReview();
    }

    /**
     * Sets a piece of media's target age using its ID.
     * @param id ID of media.
     * @param targetAge New target age of media.
     */
    public void setTargetAge(String id, String targetAge) {
        Media media = searchMedia(id);
        //media.changeTargetAge(targetAge); UNCOMMENT WHEN AADHYA ADDS THE METHOD.
    }

    /**
     * Resets currentList to be identical to mediaList.
     */
    public void resetCurrentList() {
        currentList.clear();
        currentList.addAll(mediaList);
    }

    /**
     * Removes a piece of media from currentList and mediaList using its ID.
     * @param id ID of media.
     */
    public void removeMedia(String id) {
        Media media = searchMedia(id);
        mediaList.remove(media);
        currentList.remove(media);
    }

    /**
     * Adds a new piece of media to mediaList.
     * @param newMedia The new Media object.
     */
    public void addMedia(Media newMedia) {
        mediaList.add(newMedia);
        //Won't add the media to currentList immediately.
    }

    /**
     * Prints all the books in currentList.
     */
    public void printBooks() {
        filterMedia("book");
        for (Media book : currentList) {
            System.out.println(book);
        }
    }

    /**
     * Prints all the TV shows in currentList.
     */
    public void printTvShows() {
        filterMedia("tvshow");
        for (Media show : currentList) {
            System.out.println(show);
        }
    }

    /**
     * Prints all the movies in currentList.
     */
    public void printMovies() {
        filterMedia("movie");
        for (Media movie : currentList) {
            System.out.println(movie);
        }
    }

    /**
     * Prints all the video games in currentList.
     */
    public void printVideoGames() {
        filterMedia("videogame");
        for (Media videogame : currentList) {
            System.out.println(videogame);
        }
    }

    /**
     * Prints all the objects in currentList.
     */
    public void printCurrentList() {
        for (Media media : currentList) {
            System.out.println(media);
        }
    }

    /**
     * Imports a list of media from a text file.
     * @param fileName Name of the imported file.
     * @return Boolean of if import was successful.
     */
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
        String[] tags;

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
                mediaInfo = line.split("~");
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
                        mediaList.add(new Book(id, name, review, rating, targetAge,
                                false, 0, description, publishing, pages, author));
                        tags = mediaInfo[9].split("_");
                        break;
                    case "T":
                        seasons = Integer.parseInt(mediaInfo[7]);
                        episodes = Integer.parseInt(mediaInfo[8]);
                        producers = mediaInfo[9].split("_");
                        director = mediaInfo[10];
                        starActors = mediaInfo[11].split("_");
                        mediaList.add(new TvShow(id, name, review, rating, targetAge,
                                false, 0, description, publishing,
                                seasons, episodes,toArrayList(producers), director, toArrayList(starActors)));
                        tags = mediaInfo[12].split("_");
                        break;
                    case "M":
                        duration = Integer.parseInt(mediaInfo[7]);
                        producers = mediaInfo[8].split("_");
                        director = mediaInfo[9];
                        starActors = mediaInfo[10].split("_");
                        mediaList.add(new Movie(id, name, review, rating, targetAge,
                                false, 0, description, publishing, duration,
                                toArrayList(producers), director, toArrayList(starActors)));
                        tags = mediaInfo[11].split("_");
                        break;
                    case "V":
                        playtime = Integer.parseInt(mediaInfo[7]);
                        devs = mediaInfo[8].split("_");
                        mediaList.add(new VideoGame(id, name, review, rating, targetAge,
                                false, 0, description, publishing,
                                playtime, toArrayList(devs)));
                        tags = mediaInfo[9].split("_");
                        break;
                    //Need to initialize 'tags' in default so next for-loop works.
                    default:
                        tags = new String[0];
                }
                for (String tag : tags) {
                    resetCurrentList();
                    searchMedia(id).tags.add(tag);
                }
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (IOException iox) {
            System.out.println("Failed to import file.");
            return false;
        }
    }

    /**
     * Exports a media list to a text file.
     * @param fileName Name of the exported file.
     * @param list The desired list to export.
     * @return Boolean of if the export was successful.
     */
    private boolean exportList(String fileName, ArrayList<Media> list) {
        String identifier;
        Book book;
        TvShow show;
        Movie movie;
        VideoGame game;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Media media : list) {
                writer.write(media.getId() + "~");
                writer.write(media.getName() + "~");
                writer.write(media.getReview() + "~");
                writer.write(media.getRating() + "~");
                writer.write(media.getTargetAge() + "~");
                writer.write(media.getIsLocked() + "~");
                writer.write(media.getWatchedStatus() + "~");
                writer.write(media.getDescription() + "~");
                writer.write(media.getPublishing() + "~");

                identifier = media.id.substring(0,1);

                switch(identifier) {
                    case "B":
                        book = (Book) media;
                        writer.write(book.getPages() + "~");
                        writer.write(book.getAuthor() + "~");
                        break;
                    case "T":
                        show = (TvShow) media;
                        writer.write(show.getSeasons() + "~");
                        writer.write(show.getEpisodes() + "~");
                        for (String producer : show.getProducers()) {
                            if (show.getProducers().indexOf(producer) == show.getProducers().size()-1) {
                                writer.write(producer + "~");
                            } else {
                                writer.write(producer + "_");
                            }
                        }
                        writer.write(show.getDirector() + "~");
                        for (String actor : show.getStarActors()) {
                            if (show.getStarActors().indexOf(actor) == show.getStarActors().size()-1) {
                                writer.write(actor + "~");
                            } else {
                                writer.write(actor + "_");
                            }
                        }
                        break;
                    case "M":
                        movie = (Movie) media;
                        writer.write(movie.getDuration() + "~");
                        for (String producer : movie.getProducers()) {
                            if (movie.getProducers().indexOf(producer) == movie.getProducers().size()-1) {
                                writer.write(producer + "~");
                            } else {
                                writer.write(producer + "_");
                            }
                        }
                        writer.write(movie.getDirector() + "~");
                        for (String actor : movie.getStarActors()) {
                            if (movie.getStarActors().indexOf(actor) == movie.getStarActors().size()-1) {
                                writer.write(actor + "~");
                            } else {
                                writer.write(actor + "_");
                            }
                        }
                        break;
                    case "V":
                        game = (VideoGame) media;
                        writer.write(game.getPlaytime() + "~");
                        for (String dev : game.getDevs()) {
                            if (game.getDevs().indexOf(dev) == game.getDevs().size()-1) {
                                writer.write(dev + "~");
                            } else {
                                writer.write(dev + "_");
                            }
                        }
                }
                for (String tag : media.getTags()) {
                    if (media.getTags().indexOf(tag) == media.getTags().size()-1) {
                        writer.write(tag);
                    } else {
                        writer.write(tag + "_");
                    }
                }
                writer.write("\n");
            }
            writer.close();
            return true;
        } catch (IOException iox) {
            System.out.println("Failed to export file.");
        }
        return false;
    }

    /**
     * Exports the mediaList to a text file.
     * @param fileName Name of the exported file.
     * @return Boolean of if export was successful.
     */
    public boolean exportMediaList(String fileName) {
        return exportList(fileName, mediaList);
    }

    /**
     * Exports the currentList to a text file.
     * @param fileName Name of the exported file.
     * @return Boolean of if export was successful.
     */
    public boolean exportCurrentList(String fileName) {
        return exportList(fileName, currentList);
    }

    /**
     * Turns a string array into an arrayList.
     * @param array The converted string array.
     * @return An arrayList that contains all elements of the string array.
     */
    private static ArrayList<String> toArrayList(String[] array) {
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }
}
