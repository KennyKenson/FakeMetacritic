import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String FILE_PATH = "src/";
    static final String DEFAULT_FILE = "mediaLib.csv";
    static final Scanner READER = new Scanner(System.in);
    static MediaLibrary mediaLib = null;

    public static void main(String[] args) {
        mediaLib = new MediaLibrary();
        mediaLib.addMedia(new Book("B1234567", "The Odyssey"));
        mediaLib.addMedia(new Movie("M1234567", "Mulan"));
        mediaLib.addMedia(new VideoGame("V1234567", "Undertale"));
        mediaLib.addMedia(new TvShow("T1234567", "Breaking Bad"));
        intro();


        String choice;
        String filename = "";

        boolean run = true;
        boolean success;

        while (run) {
            printMenu();

            if (mediaLib != null) {
                System.out.print("> ");
                choice = READER.nextLine();
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
                        System.out.println("Select a media type to add:\n1 > Book\n2 > Movie\n3 > Video Game\n4 > TV Show");
                        System.out.print("> ");
                        choice = READER.nextLine();
                        switch (choice) {
                            case "1": {
                                String id = "B";

                                System.out.print("Enter the name of the book: ");
                                String name = READER.nextLine();

                                System.out.print("Enter the author of the book: ");
                                String author = READER.nextLine();

                                System.out.print("Enter the number of pages in the book: ");
                                int pages = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the target age of the book: ");
                                int age = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the book's description: ");
                                String desc = READER.nextLine();

                                System.out.print("Enter the book's publishing house: ");
                                String pub_house = READER.nextLine();

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
                                String id = "T";

                                System.out.print("Enter the name of the movie: ");
                                String name = READER.nextLine();

                                System.out.print("Enter the target age of the movie: ");
                                int age = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the movie's description: ");
                                String desc = READER.nextLine();

                                System.out.print("Enter the movie's studio: ");
                                String studio = READER.nextLine();

                                System.out.print("Enter the duration of the movie: ");
                                int duration = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the movie's director(s): ");
                                String director = READER.nextLine();

                                String[] producers = {};// create a method to get string arrays

                                String[] starActors = {};// create a method to get string arrays

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

                                System.out.print("Enter the name of the video game: ");
                                String name = READER.nextLine();

                                System.out.print("Enter the target age of the video game: ");
                                int age = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the video game's description: ");
                                String desc = READER.nextLine();

                                System.out.print("Enter the video game's studio: ");
                                String studio = READER.nextLine();

                                System.out.print("Enter the expected duration of the video game: ");
                                int playtime = Integer.parseInt(READER.nextLine());

                                String[] devs = {"help"};// create a method to get string arrays

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(devs[0].charAt(0)) +
                                        Integer.toString(playtime).charAt(0) + Integer.toString(age).charAt(0) +
                                        Integer.toString(desc.charAt(0)) + Integer.toString(studio.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new VideoGame(id, name, age, desc, studio, playtime, devs);
                                invalid_choice = false;
                                break;
                            }
                            case "4": {
                                String id = "T";

                                System.out.print("Enter the name of the TV show: ");
                                String name = READER.nextLine();

                                System.out.print("Enter the target age of the TV show: ");
                                int age = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the TV show's description: ");
                                String desc = READER.nextLine();

                                System.out.print("Enter the TV show's studio: ");
                                String studio = READER.nextLine();

                                System.out.print("Enter the number of seasons in the TV show: ");
                                int seasons = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the number of episodes in the TV show: ");
                                int episodes = Integer.parseInt(READER.nextLine());

                                System.out.print("Enter the TV show's director(s): ");
                                String director = READER.nextLine();

                                String[] producers = {};// create a method to get string arrays

                                String[] starActors = {};// create a method to get string arrays

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(director.charAt(0)) + Integer.toString(seasons).charAt(0) +
                                        Integer.toString(age).charAt(0) + Integer.toString(desc.charAt(0)) +
                                        Integer.toString(studio.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new TvShow(id, name, age, desc, studio, seasons, episodes, producers, director, starActors);
                                invalid_choice = false;
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice. Try again. ");
                            }
                        }
                    }

                    mediaLib.addMedia(m);
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
                        System.out.println("Rate the media from 0-5 stars: ");
                        System.out.print("> ");
                        int score = Integer.parseInt(READER.nextLine());

                        mediaLib.rateMedia(id, score);
                    }

                    break;

                }

                case "4": {
                    String id = unlockMedia(getValidMedia("Select a media to review. Type either the media's name or it's ID: ", mediaLib.getMediaList()));

                    if (id != null){
                        System.out.println("Leave a review for the media: ");
                        System.out.print("> ");
                        String review = READER.nextLine();

                        mediaLib.reviewMedia(id, review);
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
                    System.out.println(mediaLib.searchMedia(id));
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

                        System.out.print("> ");
                        choice = READER.nextLine();
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
                    System.out.print("> ");
                    choice = READER.nextLine();
                    boolean isInvalid = true;

                    while (isInvalid) {
                        switch (choice) {
                            case "1": {
                                System.out.println("Enter the minimum age: ");
                                System.out.print("> ");
                                int age = Integer.parseInt(READER.nextLine());
                                mediaLib.filterMedia(age);

                                isInvalid = false;
                                break;
                            }
                            case "2": {
                                System.out.println("Enter the minimum rating: ");
                                System.out.print("> ");
                                int rating  = Integer.parseInt(READER.nextLine());
                                mediaLib.filterMedia(rating);

                                isInvalid = false;
                                break;
                            }
                            case "3": {
                                System.out.println("Enter a keyword: ");
                                System.out.print("> ");
                                String keyword = READER.nextLine();
                                mediaLib.filterMedia(keyword);

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

                    System.out.print("> ");
                    choice = READER.nextLine();
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
                    mediaLib.printBooks();
                    break;
                }

                case "15": {
                    mediaLib.printTvShows();
                    break;
                }

                case "16": {
                    mediaLib.printMovies();
                    break;
                }

                case "17": {
                    mediaLib.printVideoGames();
                    break;
                }

                case "18": {
                    success = false;
                    mediaLib = new MediaLibrary();
                    while (!success) {
                        System.out.printf("Enter a filename to import (default location: %s): ", FILE_PATH);
                        filename = READER.nextLine();
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
                        System.out.print("Do you want to use the same filename ("
                                + filename + ")? (y/n) ");
                        choice = READER.nextLine();
                        if (!choice.equals("y")) {
                            System.out.print("Enter a filename to save: ");
                            filename = READER.nextLine();
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

            System.out.print("> ");
            String input = READER.nextLine();

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
        String choice;
        Media m = mediaLib.searchMedia(id);

        if (m.getIsLocked()){
            System.out.println("The selected media is locked. Would you like to unlock it?(Y/N)");
            System.out.print("> ");
            choice = READER.nextLine();
            if (choice.equals("Y") || choice.equals("y")){
                m.lockMedia(false);
                return id;
            }
        }

        return null;
    }

    public static void next() {
        System.out.println();
        System.out.print("Press Enter to continue...");
        READER.nextLine();
        System.out.println();
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

            ArrayList<Media> currentList = mediaLib.currentList;

            if (currentList.isEmpty()) {
                System.out.println("Current list has no elements. Retrieving information from media library");
                mediaLib.currentList = mediaLib.getMediaList();
                currentList = mediaLib.currentList;
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