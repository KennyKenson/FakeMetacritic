import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String FILE_PATH = "src/";
    static final String DEFAULT_FILE = "mediaLib.csv";
    static final Scanner READER = new Scanner(System.in);
    static MediaLibrary mediaLib = null;

    static final String BACK_VALUE = "back";

    public static void main(String[] args) {
        mediaLib = new MediaLibrary();
        mediaLib.addMedia(new Book("B1234567", "The Odyssey"));
        mediaLib.addMedia(new Movie("M1234567", "Mulan"));
        mediaLib.addMedia(new VideoGame("V1234567", "Undertale"));
        mediaLib.addMedia(new TvShow("T1234567", "Breaking Bad"));
        mediaLib.resetCurrentList(); //Need to load info from mediaList onto currentList.
//        intro();

        String choice;
        String filename = "";

        boolean run = true;
        boolean success;

        while (run) {
            printMenu();

            if (mediaLib != null) {
                choice = getString();
            } else {
                System.out.println("No file has been detected in the system. Please load a file to continue.");
                choice = "18";
            }

            switch (choice) {
                case "0": {     // Exit.
                    System.out.println("Leaving Fake Metacritic...");
                    run = false;
                    break;
                }

                case "1": {     // Load information from file.

                    boolean invalid_choice = true;

                    Media m = null;

                    while (invalid_choice) {
                        choice = getString("""
Select a media type to add:
1 > Book
2 > Movie
3 > Video Game
4 > TV Show
Type "back" to leave.""");
                        switch (choice) {
                            case "1": {
                                String id = "B";

                                String name = getString("Enter the name of the book: ");

                                String author = getString("Enter the author of the book: ");

                                int pages = getInt("Enter the number of pages in the book: ");

                                int age = getInt("Enter the target age of the book: ");

                                String desc = getString("Enter the book's description: ");

                                String pub_house = getString("Enter the book's publishing house: ");

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(author.charAt(0)) + Integer.toString(pages).charAt(0) +
                                        Integer.toString(age).charAt(0) + Integer.toString(desc.charAt(0)) +
                                        Integer.toString(pub_house.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new Book(id, name, author, pages, age, desc, pub_house);

                                invalid_choice = false;
                                break;
                            }
                            case "2": {
                                String id = "M";

                                String name = getString("Enter the name of the movie: ");

                                int age = getInt("Enter the target age of the movie: ");

                                String desc = getString("Enter the movie's description: ");

                                String studio = getString("Enter the movie's studio: ");

                                int duration = getInt("Enter the duration of the movie: ");

                                String director = getString("Enter the movie's director(s): ");

                                ArrayList<String> producers = getArraylist("Enter the producers of the movie.");

                                ArrayList<String> starActors = getArraylist("Enter the star actors of the movie.");

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(director.charAt(0)) + Integer.toString(duration).charAt(0) +
                                        Integer.toString(age).charAt(0) + Integer.toString(desc.charAt(0)) +
                                        Integer.toString(studio.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new Movie(id, name, age, desc, studio, duration, producers, director, starActors);
                                invalid_choice = false;
                                break;
                            }
                            case "3": {
                                String id = "V";

                                String name = getString("Enter the name of the video game: ");

                                int age = getInt("Enter the target age of the video game: ");

                                String desc = getString("Enter the video game's description: ");

                                String studio = getString("Enter the video game's studio: ");

                                int playtime = getInt("Enter the expected duration of the video game: ");

                                ArrayList<String> devs = getArraylist("Enter the developers of the game.");// create a method to get string arrays

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(devs.getFirst().charAt(0)) +
                                        Integer.toString(playtime).charAt(0) + Integer.toString(age).charAt(0) +
                                        Integer.toString(desc.charAt(0)) + Integer.toString(studio.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new VideoGame(id, name, age, desc, studio, playtime, devs);
                                invalid_choice = false;
                                break;
                            }
                            case "4": {
                                String id = "T";

                                String name = getString("Enter the name of the TV show: ");

                                int age = getInt("Enter the target age of the TV show: ");

                                String desc = getString("Enter the TV show's description: ");

                                String studio = getString("Enter the TV show's studio: ");

                                int seasons = getInt("Enter the number of seasons in the TV show: ");

                                int episodes = getInt("Enter the number of episodes in the TV show: ");

                                String director = getString("Enter the TV show's director(s): ");

                                ArrayList<String> producers = getArraylist("Enter the producers of the TV Show.");

                                ArrayList<String> starActors = getArraylist("Enter the star actors of the TV Show.");

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(director.charAt(0)) + Integer.toString(seasons).charAt(0) +
                                        Integer.toString(age).charAt(0) + Integer.toString(desc.charAt(0)) +
                                        Integer.toString(studio.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new TvShow(id, name, age, desc, studio, seasons, episodes, producers, director, starActors);
                                invalid_choice = false;
                                break;
                            }
                            case BACK_VALUE :{
                                invalid_choice = false;
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice. Try again. ");
                            }
                        }
                    }

                    if (m != null) {
                        mediaLib.addMedia(m);
                    }
                    break;
                }

                case "2": {

                    String id = unlockMedia(getValidMedia("Select a media to remove. Type either the media's name or it's ID: ", mediaLib.getMediaList()));

                    if (id != null) {
                        mediaLib.removeMedia(id);
                        System.out.println("Media removed successfully.");
                    }

                    break;
                }

                case "3": {
                    String id = unlockMedia(getValidMedia("Select a media to rate. Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                    if (id != null) {
                        int score = getInt("Rate the media from 0-5 stars: ");
                        mediaLib.rateMedia(id, score);
                        System.out.println("Rating was successful!");
                    }

                    break;

                }

                case "4": {
                    String id = unlockMedia(getValidMedia("Select a media to review. Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                    if (id != null){
                        String review = getString("Leave a review for the media: ");
                        mediaLib.reviewMedia(id, review);
                        System.out.println("Review was successful!");

                    }

                    break;
                }

                case "5": {
                    String id = unlockMedia(getValidMedia("Select a media to remove rating. Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                    if (id != null){
                        mediaLib.removeRating(id);
                        System.out.println("Rating successfully removed. ");
                    }

                    break;
                }

                case "6": {
                    String id = unlockMedia(getValidMedia("Select a media to remove review. Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                    if (id != null){
                        mediaLib.removeReview(id);
                        System.out.println("Review successfully removed. ");
                    }
                    break;
                }

                case "7": {
                    ArrayList<Media> media = new ArrayList<>();

                    for (Media m : mediaLib.getMediaList()){
                        if (!m.isLocked){
                            media.add(m);
                        }
                    }

                    if (!media.isEmpty()) {
                        String id = getValidMedia("Select a media to lock. Type either the media's name or it's ID: ", media);

                        mediaLib.lockMedia(id);
                        System.out.println("Media successfully locked. ");
                    }

                    break;
                }

                case "8": {
                    Media randMedia = mediaLib.randomMedia();

                    System.out.println("Here's a random media: ");

                    System.out.println(randMedia);

                    break;
                }

                case "9": {
                    String id = getValidMedia("Select a media to view Type either the media's name or it's ID:", mediaLib.getMediaList());
                    Media m = mediaLib.searchMedia(id);
                    System.out.println(m);
                    break;
                }

                case "10": {
                    String id = unlockMedia(getValidMedia("Select a media to change watched status of. Type either the media's name or it's ID: ", mediaLib.getMediaList()));

                    if (id != null){
                        Media m = mediaLib.searchMedia(id);

                        System.out.println("Select a status: ");
                        System.out.println(" 0 > " + Media.WATCHED_STATUS_STRINGS[0]);
                        System.out.println(" 1 > " + Media.WATCHED_STATUS_STRINGS[1]);
                        System.out.println(" 2 > " + Media.WATCHED_STATUS_STRINGS[2]);
                        System.out.println(" 3 > " + Media.WATCHED_STATUS_STRINGS[3]);
                        System.out.println(" 4 > " + Media.WATCHED_STATUS_STRINGS[4]);

                        choice = getString();
                        boolean isInvalid = true;

                        while (isInvalid) {
                            switch (choice) {
                                case "0": {
                                    m.changeWatchedStatus(0);
                                    isInvalid = false;
                                    break;
                                }
                                case "1": {
                                    m.changeWatchedStatus(1);
                                    isInvalid = false;
                                    break;
                                }
                                case "2": {
                                    m.changeWatchedStatus(2);
                                    isInvalid = false;
                                    break;
                                }
                                case "3": {
                                    m.changeWatchedStatus(3);
                                    isInvalid = false;
                                    break;
                                }
                                case "4": {
                                    m.changeWatchedStatus(4);
                                    isInvalid = false;
                                    break;
                                }
                                case BACK_VALUE :{
                                    isInvalid = false;
                                    break;
                                }
                                default: {
                                    System.out.println("Invalid option. Try again.");
                                }
                            }
                        }
                    }
                    break;
                }

                case "11": {
                    System.out.println("Select what you want to filter: ");
                    System.out.println(" 1 > target age");
                    System.out.println(" 2 > rating");
                    System.out.println(" 3 > keyword");
                    choice = getString();
                    boolean isInvalid = true;

                    while (isInvalid) {
                        switch (choice) {
                            case "1": {
                                int age = getInt("Enter the minimum age: ");
                                mediaLib.filterMedia(age);

                                isInvalid = false;
                                break;
                            }
                            case "2": {
                                int rating  = getInt("Enter the minimum rating: ");
                                mediaLib.filterRating(rating);

                                isInvalid = false;
                                break;
                            }
                            case "3": {
                                String keyword = getString("Enter a keyword: ");
                                mediaLib.filterMedia(keyword);

                                isInvalid = false;
                                break;
                            }
                            case BACK_VALUE :{
                                isInvalid = false;
                                break;
                            }
                            default: {
                                System.out.println("Invalid option. Try again.");
                            }
                        }
                    }

                    System.out.println("Current list successfully filtered");


                    break;
                }

                case "12": {
                    System.out.println("Select the following ways to sort Current List: ");
                    System.out.println(" 1 > Sort by name");
                    System.out.println(" 2 > Sort by rating");
                    System.out.println(" 3 > Sort by ID");

                    choice = getString();
                    boolean isInvalid = true;

                    while (isInvalid) {
                        switch (choice) {
                            case "1": {
                                mediaLib.sortMediaByName();
                                isInvalid = false;
                                break;
                            }
                            case "2": {
                                mediaLib.sortMediaByRating();
                                isInvalid = false;
                                break;
                            }
                            case "3": {
                                mediaLib.sortMediaById();
                                isInvalid = false;
                                break;
                            }
                            case BACK_VALUE :{
                                isInvalid = false;
                                break;
                            }
                            default: {
                                System.out.println("Invalid option. Try again.");
                            }
                        }
                    }
                    break;
                }

                case "13": {
                    mediaLib.resetCurrentList();
                    System.out.println("Current list reset successfully.");
                    break;
                }

                case "14": {
                    mediaLib.resetCurrentList();
                    mediaLib.printBooks();
                    break;
                }

                case "15": {
                    mediaLib.resetCurrentList();
                    mediaLib.printTvShows();
                    break;
                }

                case "16": {
                    mediaLib.resetCurrentList();
                    mediaLib.printMovies();
                    break;
                }

                case "17": {
                    mediaLib.resetCurrentList();
                    mediaLib.printVideoGames();
                    break;
                }

                case "18": {
                    success = false;
                    mediaLib = new MediaLibrary();
                    while (!success) {
                        filename = getString("Enter a filename to import (default location: " + FILE_PATH + "): ");
                        if (filename.isEmpty()) {
                            filename = DEFAULT_FILE;
                        }
                        success = mediaLib.importMediaList(FILE_PATH + filename);
                        if (success) {
                            System.out.println("Media successfully loaded.\n ");
                        } else {
                            System.out.println("Media unsuccessfully loaded. ");
                        }
                    }
                    break;
                }

                case "19": {
                    success = false;
                    while (!success) {
                        choice = getString("Do you want to use the same filename (" + filename + ")? (y/n) ");
                        if (!choice.equals("y")) {
                            filename = getString("Enter a filename to save: ");
                        }
                        System.out.println("Saving file...");
                        success = mediaLib.exportMediaList(FILE_PATH + filename);
                        if (success) {
                            System.out.println("Media list saved!");
                        } else {
                            System.out.println("Media list not saved!");
                        }
                    }
                    System.out.println();
                    break;
                }
                default: {
                    System.out.println("\nInvalid choice!\n");
                }
            }
            next();
        }

    }

    public static Integer getInt(String msg) {
        System.out.println(msg);
        System.out.print("> ");
        int n;
        while (true) {
            try {
                n = Integer.parseInt(READER.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please type a number. ");
            }
        }
        return n;
    }
    public static String getString (String msg){
        System.out.println(msg);
        System.out.print("> ");
        return READER.nextLine();
    }
    public static String getString (){
        System.out.print("> ");
        return READER.nextLine();
    }
    public static ArrayList<String> getArraylist(String msg){
        System.out.println(msg + " Enter -1 to stop. ");
        String input;
        ArrayList<String> list = new ArrayList<>();

        while (true){
            System.out.print("> ");
            input = READER.nextLine();
            if (input.equals("-1")){
                break;
            } else {
                list.add(input);
            }
        }

        return list;
    }

    public static String getValidMedia(String msg, ArrayList<Media> media){

        System.out.println(msg);
        System.out.println("""
╔══════════════════╦══════════════════╦════════════╦══════════════╦════════════╦════════════╦═════════╗
║       Name       ║        ID        ║   Type     ║  Age Rating  ║   Rating   ║   Status   ║ Locked? ║
╠══════════════════╬══════════════════╬════════════╬══════════════╬════════════╬════════════╬═════════╣""");
        for (Media m : media){
            m.printDetails();
        }
        System.out.println("╚══════════════════╩══════════════════╩════════════╩══════════════╩════════════╩════════════╩═════════╝");

        String id = null;

        while (id == null){

            String input = getString();

            if (mediaLib.findMedia(input) != null){
                id = mediaLib.findMedia(input);
            } else if (mediaLib.searchMedia(input) != null) {
                id = input;
            }

            if (id == null){
                System.out.println("Invalid input. Please enter either the name or the ID of the media you want to remove.");
            }

        }

        return id;
    }

    public static String unlockMedia(String id){
        String input;
        Media m = mediaLib.searchMedia(id);

        if (m.getIsLocked()){
            input = getString("The selected media is locked. Would you like to unlock it?(Y/N)");
            if (input.equals("Y") || input.equals("y")){
                m.lockMedia(false);
                return id;
            }
        } else {
            return id;
        }

        return null;
    }

    public static void next() {
        getString("Press enter to continue: ");
    }

    public static void intro() {
        System.out.println("Ladies, gentlemen, and Mr. Cho...");
        next();
        System.out.println("Aadhya and Ken presents...");
        next();
        System.out.println("A dataset that will blow your minds!");
        next();
        System.out.println("Introducing.....");
        System.out.println("*insert drum roll effect here*");
        next();
        System.out.println("""
                                            
            ███████╗ █████╗ ██╗  ██╗███████╗    ███╗   ███╗███████╗████████╗ █████╗  ██████╗██████╗ ██╗████████╗██╗ ██████╗
            ██╔════╝██╔══██╗██║ ██╔╝██╔════╝    ████╗ ████║██╔════╝╚══██╔══╝██╔══██╗██╔════╝██╔══██╗██║╚══██╔══╝██║██╔════╝
            █████╗  ███████║█████╔╝ █████╗      ██╔████╔██║█████╗     ██║   ███████║██║     ██████╔╝██║   ██║   ██║██║    \s
            ██╔══╝  ██╔══██║██╔═██╗ ██╔══╝      ██║╚██╔╝██║██╔══╝     ██║   ██╔══██║██║     ██╔══██╗██║   ██║   ██║██║    \s
            ██║     ██║  ██║██║  ██╗███████╗    ██║ ╚═╝ ██║███████╗   ██║   ██║  ██║╚██████╗██║  ██║██║   ██║   ██║╚██████╗
            ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝    ╚═╝     ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝ ╚═════╝""");
        next();

    }

    public static void printMenu() {
        if (mediaLib != null) {

            ArrayList<Media> currentList = mediaLib.getCurrentList();

            if (currentList.isEmpty()) {
                System.out.println("Current list has no elements. Retrieving information from media library");
                mediaLib.resetCurrentList();
                currentList = mediaLib.getCurrentList();
            }
            System.out.println("""
╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗
║                                        Current List Of Media                                        ║
╠══════════════════╦══════════════════╦════════════╦══════════════╦════════════╦════════════╦═════════╣
║       Name       ║        ID        ║    Type    ║  Age Rating  ║   Rating   ║   Status   ║ Locked? ║
╠══════════════════╬══════════════════╬════════════╬══════════════╬════════════╬════════════╬═════════╣"""
           );
            for (Media m : currentList) {

                m.printDetails();
            }

            System.out.println("╚══════════════════╩══════════════════╩════════════╩══════════════╩════════════╩════════════╩═════════╝");

            System.out.println("""
Select an option:

 --Media Handling--           --Edit Current List--           --View All--           --Import/Export--
  1 > add media               11 > filter media 	          14 > Books	         18 > Import
  2 > remove media            12 > sort media          		  15 > TV Shows	         19 > Export
  3 > rate media	          13 > reset Current List         16 > Movies
  4 > review media                                            17 > Video Games
  5 > remove rating
  6 > remove review
  7 > lock media
  8 > get random media
  9 > find media
 10 > change watched status
  
  0 > exit
 """);
        }
    }
}