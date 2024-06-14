import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String FILE_PATH = "src/";
    static final Scanner READER = new Scanner(System.in);
    static MediaLibrary mediaLib = null;
    static final String BACK_VALUE = "back";

    public static void main(String[] args) {
        intro();

        String choice;
        String filename = null;


        boolean run = true;
        boolean success;
        boolean isEmpty = true;

        while (run) {

            if (mediaLib == null) {
                System.out.println("No file has been detected in the system. Please load a file to continue.");
                choice = "21";
            } else {
                filename = mediaLib.DEFAULT_FILE;
                isEmpty = mediaLib.getMediaList().isEmpty();
                printMenu();
                choice = getString();
            }

            if (isEmpty && !choice.equals("0") && !choice.equals("1") && !choice.equals("21") && !choice.equals("22")) {
                System.out.println("Media library is empty. Please add media before continuing with any operations. ");
                System.out.println("You can currently add media (1), import file (21), export file (21), and exit (0)");
                choice = null;
            }

            switch (choice) {
                case "0": {     // Exit.
                    System.out.println("Leaving Fake Metacritic...");
                    run = false;
                    break;
                }

                case "1": {     // Adds media to media library

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

                                int duration = getInt("Enter the duration of the movie in minutes: ");

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

                                int playtime = getInt("Enter the expected duration of the video game in minutes: ");

                                ArrayList<String> devs = getArraylist("Enter the developers of the game.");

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(devs.get(0).charAt(0)) +
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

                                String creator = getString("Enter the TV show's creator(s): ");

                                ArrayList<String> producers = getArraylist("Enter the producers of the TV Show.");

                                ArrayList<String> starActors = getArraylist("Enter the star actors of the TV Show.");

                                String tail_end = (Integer.toString(name.charAt(0)) +
                                        Integer.toString(creator.charAt(0)) + Integer.toString(seasons).charAt(0) +
                                        Integer.toString(age).charAt(0) + Integer.toString(desc.charAt(0)) +
                                        Integer.toString(studio.charAt(0)));

                                id += tail_end.toLowerCase();

                                m = new TvShow(id, name, age, desc, studio, seasons, episodes, producers,
                                        creator, starActors);
                                invalid_choice = false;
                                break;
                            }
                            case BACK_VALUE: {
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
                        System.out.println("Media added successfully!");
                    }
                    break;
                }

                case "2": {

                    String id = unlockMedia(getValidMedia("Select a media to remove. " +
                            "Type either the media's name or it's ID: ", mediaLib.getMediaList()));

                    if (id != null) {
                        mediaLib.removeMedia(id);
                        System.out.println("Media removed successfully.");
                        mediaLib.resetCurrentList();
                    }

                    break;
                }

                case "3": {
                    String id = unlockMedia(getValidMedia("Select a media to rate. " +
                            "Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                    if (id != null) {
                        boolean isInvalid = true;
                        int score = -1;
                        while (isInvalid) {
                            score = getInt("Rate the media from 0-5 stars: ");
                            if (score >= 0 && score <= 5) {
                                isInvalid = false;
                            }
                            if (isInvalid) {
                                System.out.println("That is an invalid rating. Please try again.");
                            }
                        }
                        mediaLib.rateMedia(id, score);
                        System.out.println("Rating was successful!");
                    }

                    break;

                }

                case "4": {
                    String id = unlockMedia(getValidMedia("Select a media to review. " +
                            "Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                    if (id != null) {
                        String review = getString("Leave a review for the media: ");
                        mediaLib.reviewMedia(id, review);
                        System.out.println("Review was successful!");

                    }

                    break;
                }

                case "5": {
                    int hasRating = 0;
                    for (Media m : mediaLib.getMediaList()){
                        if (!m.getReview().isBlank()){
                            hasRating++;
                        }
                    }
                    if (hasRating == 0){
                        System.out.println("None of the media in the library has a rating to remove.");
                    } else {
                        String id = null;
                        boolean isValid = false;
                        while (!isValid) {
                            id = unlockMedia(getValidMedia("Select a media to remove rating. " +
                                    "Type either the media's name or it's ID: ", mediaLib.getMediaList()));
                            Media m = mediaLib.searchMedia(id);
                            if (m.getReview().isBlank()) {
                                isValid = true;
                            } else {
                                System.out.println("The media selected already has no rating. Select a different media.");
                            }
                        }
                        if (id != null) {
                            mediaLib.removeReview(id);
                            System.out.println("Review successfully removed. ");
                        }
                    }

                    break;
                }

                case "6": {
                    int hasReview = 0;
                    for (Media m : mediaLib.getMediaList()){
                        if (!m.getReview().isBlank()){
                            hasReview++;
                        }
                    }
                    if (hasReview == 0){
                        System.out.println("None of the media in the library has a rating to remove.");
                    } else {
                        String id = null;
                        boolean isValid = false;
                        while (!isValid) {
                            id = unlockMedia(getValidMedia("Select a media to remove the rating. " +
                                    "Type either the media's name or its ID: ", mediaLib.getMediaList()));
                            Media m = mediaLib.searchMedia(id);
                            if (m.getReview().isBlank()){
                                isValid = true;
                            } else {
                                System.out.println("The media selected already has no review. Select a different media.");
                            }
                        }
                        if (id != null) {
                            mediaLib.removeReview(id);
                            System.out.println("Review successfully removed. ");
                        }
                    }
                    break;
                }

                case "7": {
                    ArrayList<Media> media = new ArrayList<>();

                    for (Media m : mediaLib.getMediaList()) {
                        if (!m.isLocked) {
                            media.add(m);
                        }
                    }

                    if (!media.isEmpty()) {
                        String id = getValidMedia("Select a media to lock. " +
                                "Type either the media's name or it's ID: ", media);

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
                    String id = getValidMedia("Select a media to view. " +
                            "Type either the media's name or it's ID:", mediaLib.getMediaList());
                    Media m = mediaLib.searchMedia(id);
                    System.out.println(m);
                    break;
                }

                case "10": {
                    String id = unlockMedia(getValidMedia("Select a media to change watched status of. " +
                            "Type either the media's name or it's ID: ", mediaLib.getMediaList()));

                    if (id != null) {
                        Media m = mediaLib.searchMedia(id);



                        boolean isInvalid = true;

                        while (isInvalid) {

                            choice = getString("Select a status: "
                                    + "\n 0 > " + Media.WATCHED_STATUS_STRINGS[0]
                                    + "\n 1 > " + Media.WATCHED_STATUS_STRINGS[1]
                                    + "\n 2 > " + Media.WATCHED_STATUS_STRINGS[2]
                                    + "\n 3 > " + Media.WATCHED_STATUS_STRINGS[3]
                                    + "\n 4 > " + Media.WATCHED_STATUS_STRINGS[4]);

                            switch (choice) {
                                case "0": {
                                    m.changeWatchedStatus(0);
                                    isInvalid = false;
                                    System.out.println("Success!");
                                    break;
                                }
                                case "1": {
                                    m.changeWatchedStatus(1);
                                    isInvalid = false;
                                    System.out.println("Success!");
                                    break;
                                }
                                case "2": {
                                    m.changeWatchedStatus(2);
                                    isInvalid = false;
                                    System.out.println("Success!");
                                    break;
                                }
                                case "3": {
                                    m.changeWatchedStatus(3);
                                    isInvalid = false;
                                    System.out.println("Success!");
                                    break;
                                }
                                case "4": {
                                    m.changeWatchedStatus(4);
                                    isInvalid = false;
                                    System.out.println("Success!");
                                    break;
                                }
                                case BACK_VALUE: {
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
                    String id = getValidMedia("Enter the media you want to add tags to: ", mediaLib.getMediaList());

                    if (id != null) {
                        Media m = mediaLib.searchMedia(id);
                        ArrayList<String> tags = getArraylist("Enter the tags for " + m.getName() + ": ");
                        m.addTags(tags);
                        System.out.println("Tags added successfully. View tags in find media (9).");
                    }

                    break;
                }

                case "12": {
                    String id = getValidMedia("Enter the media you want to change the target age of: ",
                            mediaLib.getMediaList());

                    if (id != null) {
                        int age = getInt("Enter the target age of the media: ");
                        mediaLib.setTargetAge(id, age);
                        System.out.println("Target age changed successfully.");
                    }

                    break;
                }

                case "13": {
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
                                System.out.println("Current list successfully filtered");
                                break;
                            }
                            case "2": {
                                int rating = getInt("Enter the minimum rating: ");
                                mediaLib.filterRating(rating);

                                isInvalid = false;
                                System.out.println("Current list successfully filtered");
                                break;
                            }
                            case "3": {
                                String keyword = getString("Enter a keyword: ");
                                mediaLib.filterMedia(keyword);

                                isInvalid = false;
                                System.out.println("Current list successfully filtered");
                                break;
                            }
                            case BACK_VALUE: {
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

                case "14": {
                    System.out.println("Select the following ways to sort Current List: ");
                    System.out.println(" 1 > Sort by name (A-Z)");
                    System.out.println(" 2 > Sort by rating (lowest to highest)");
                    System.out.println(" 3 > Sort by ID (A-Z)");

                    choice = getString();
                    boolean isInvalid = true;

                    while (isInvalid) {
                        switch (choice) {
                            case "1": {
                                mediaLib.sortMediaByName();
                                isInvalid = false;
                                System.out.println("Current list successfully sorted.");
                                break;
                            }
                            case "2": {
                                mediaLib.sortMediaByRating();
                                isInvalid = false;
                                System.out.println("Current list successfully sorted.");
                                break;
                            }
                            case "3": {
                                mediaLib.sortMediaById();
                                isInvalid = false;
                                System.out.println("Current list successfully sorted.");
                                break;
                            }
                            case BACK_VALUE: {
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

                case "15": {
                    mediaLib.resetCurrentList();
                    System.out.println("Current list reset successfully.");
                    break;
                }

                case "16": {
                    mediaLib.reverseList();
                    System.out.println("List successfully reversed.");
                    break;
                }

                case "17": {
                    mediaLib.resetCurrentList();
                    mediaLib.printBooks();
                    break;
                }

                case "18": {
                    mediaLib.resetCurrentList();
                    mediaLib.printTvShows();
                    break;
                }

                case "19": {
                    mediaLib.resetCurrentList();
                    mediaLib.printMovies();
                    break;
                }

                case "20": {
                    mediaLib.resetCurrentList();
                    mediaLib.printVideoGames();
                    break;
                }

                case "21": {
                    success = false;
                    mediaLib = new MediaLibrary();
                    while (!success) {
                        System.out.println("Enter a filename to import. Press enter to use the default file (" +
                                mediaLib.DEFAULT_FILE + ".txt): ");
                        filename = getString();

                        if (filename.isEmpty()) {
                            filename = mediaLib.DEFAULT_FILE;
                        }
                        success = mediaLib.importMediaList(FILE_PATH + filename + ".txt");
                        if (success) {
                            System.out.println("Media successfully loaded.\n ");
                        } else {
                            System.out.println("Media unsuccessfully loaded. ");
                        }
                    }
                    break;
                }

                case "22": {
                    String whichList;
                    success = false;
                    while (true) {
                        whichList = getString("""
                                Which list do you want to export?:
                                1 > List of all media
                                2 > Current list""");
                        if (whichList.equals("1") || whichList.equals("2")) {
                            break;
                        } else if (whichList.equals(BACK_VALUE)) {
                            success = true;
                            break;
                        }
                        System.out.println("Invalid choice. Try again. ");
                    }
                    while (!success) {
                        choice = getString("Do you want to use the same filename (" + filename + ".txt)? (y/n) ");
                        if (!choice.equals("y")) {
                            filename = getString("Enter a filename to save: ");
                        }
                        System.out.println("Saving file...");
                        if (whichList.equals("1")) {
                            success = mediaLib.exportMediaList(FILE_PATH + filename + ".txt");
                        } else {
                            success = mediaLib.exportCurrentList(FILE_PATH + filename + ".txt");
                        }
                        if (success) {
                            System.out.println("Media list saved!");
                        } else {
                            System.out.println("Media list not saved!");
                        }
                    }
                    System.out.println();
                    break;
                }
                case null: {
                    // if media library is empty, we don't want the user to do anything except add media, import,
                    // export, and exit.
                    break;
                }
                default: {
                    System.out.println("\nInvalid choice!\n");
                }
            }
            if (choice == null || !choice.equals("0")) {
                next();
            }
        }
        credits();
    }

    /**
     * Gets valid integer from user.
     *
     * @param msg Message shown to user.
     * @return Integer value.
     */
    public static Integer getInt(String msg) {
        int n;
        while (true) {
            try {

                System.out.println(msg);
                System.out.print("> ");
                n = Integer.parseInt(READER.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please type a number. ");
            }
        }
        return n;
    }

    /**
     * Gets a string from user.
     *
     * @param msg Message shown to user.
     * @return String from user.
     */
    public static String getString(String msg) {
        boolean isValid = false;
        String n = null;
        while (!isValid) {
            System.out.println(msg);
            System.out.print("> ");
            n = READER.nextLine();
            if (!n.isBlank()){
                isValid = true;
            } else {
                System.out.println("Can't have an empty input.");
            }
        }
        return n;
    }

    /**
     * Gets a string from user, without showing them a message.
     *
     * @return String from user.
     */
    public static String getString() {
        System.out.print("> ");
        return READER.nextLine();
    }

    /**
     * Gets an arraylist of strings from user.
     *
     * @param msg Message shown to user.
     * @return Arraylist of strings from user.
     */
    public static ArrayList<String> getArraylist(String msg) {
        System.out.println(msg + " Enter -1 to stop. ");
        String input;
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            System.out.print("> ");
            input = READER.nextLine();
            if (input.equals("-1")) {
                break;
            } else {
                if (!input.isEmpty()) {
                    list.add(input);
                }
            }
        }

        return list;
    }

    /**
     * Gets valid Media object from user when they input the media's name or ID.
     *
     * @param msg   Message shown to user.
     * @param media The Media object.
     * @return Valid Media object.
     */
    public static String getValidMedia(String msg, ArrayList<Media> media) {

        System.out.println(msg);
        System.out.println("""
                ╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                ║                                           Current List Of Media                                           ║
                ╠══════════════════╦══════════════════╦════════════╦══════════════╦════════════╦══════════════════╦═════════╣
                ║       Name       ║        ID        ║    Type    ║  Age Rating  ║   Rating   ║      Status      ║ Locked? ║
                ╠══════════════════╬══════════════════╬════════════╬══════════════╬════════════╬══════════════════╬═════════╣""");
        for (Media m : media) {
            m.printDetails();
        }
        System.out.println("╚══════════════════╩══════════════════╩════════════╩══════════════╩════════════╩══════════════════╩═════════╝");

        String id = null;

        while (id == null) {

            String input = getString();

            if (mediaLib.findMedia(input) != null) {
                id = mediaLib.findMedia(input);
            } else if (mediaLib.searchMedia(input) != null) {
                id = input;
            }

            if (id == null) {
                System.out.println("Invalid input. Please enter either " +
                        "the name or the ID of the media you want to remove.");
            }

        }

        return id;
    }

    /**
     * Interface to unlock a piece of media.
     *
     * @param id ID for a Media object.
     * @return The ID of the target Media object.
     */
    public static String unlockMedia(String id) {
        String input;
        Media m = mediaLib.searchMedia(id);

        if (m.getIsLocked()) {
            input = getString("The selected media is locked. Would you like to unlock it?(Y/N)");
            if (input.equals("Y") || input.equals("y")) {
                mediaLib.lockMedia(id);
                return id;
            }
        } else {
            return id;
        }

        return null;
    }

    /**
     * Prints an intermediate message before going to the next screen.
     */
    public static void next() {
        System.out.println("Press enter to continue: ");
        getString();
    }

    /**
     * Introductory message when the program starts.
     */
    public static void intro() {
        System.out.println("Ladies, gentlemen, and Mr. Cho...");
        next();
        System.out.println("Aadhya and Ken presents...");
        next();
        System.out.println("A database system that will blow your minds!");
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

    /**
     * Prints the main menu of Fake Metacritic.
     */
    public static void printMenu() {
        if (mediaLib != null) {

            mediaLib.printCurrentList();

            System.out.println("""
                    Select an option:

                     --Media Handling--           --Edit Current List--           --View All--           --Import/Export--
                      1 > add media               13 > filter media 	          17 > Books	         21 > Import
                      2 > remove media            14 > sort media          		  18 > TV Shows	         22 > Export
                      3 > rate media              15 > reset Current List         19 > Movies
                      4 > review media            16 > reverse Current List       20 > Video Games
                      5 > remove rating
                      6 > remove review
                      7 > lock media
                      8 > get random media
                      9 > find media
                     10 > change watched status
                     11 > add tags to media
                     12 > change target age
                     
                     0 > exit
                     """);
        }
    }

    public static void credits() {
        System.out.println("""
                A database system by Aadhya Anand and Ken Chignell-Mizobuchi.
                Fake Metacritic is not affiliated with metacritic.com
                This is a culminating project for ICS4U0-2 taught by Fredrick Cho.
                Fancy ASCII text was generated from https://patorjk.com/software/taag/
                """);
    }
}