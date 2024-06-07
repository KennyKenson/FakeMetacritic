import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> currentList = new ArrayList<>();
    static final String FILE_PATH = "src/";
    static final String DEFAULT_FILE = "mediaLib.csv";
    static final Scanner READER = new Scanner(System.in);
    static MediaLibrary mediaLib = null;

    public static void main(String[] args) {
        runTest();
        /*if (!isLocked) {
            review = "";
        } else {
            System.out.println("The selected media is currently locked. Unlock in order to modify anything. ");
        }*/

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
                System.out.println("Load file to ");
                choice = "1";
            }

            switch (choice) {
                case "0": {     // Exit.
                    System.out.println("Leaving Fake Metacritic...");
                    run = false;
                    break;
                }

                case "1": {     // Load information from file.
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
                            System.out.println("Media successfully loaded. ");
                            System.out.println("");
                        } else {
                            System.out.println("Media unsuccessfully loaded. ");
                        }
                    }
                    break;
                }

                case "2": {     // Save information to file.
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
                            System.out.println("Staff list saved!");
                        } else {
                            System.out.println("Staff list not saved!");
                        }
                    }
                    System.out.println("");
                    break;
                }

                case "3": {
                    break;
                }

                case "4": {
                    break;
                }

                case "5": {
                    break;
                }

                case "6": {
                    break;
                }

                case "7": {
                    break;
                }

                case "8": {
                    break;
                }

                default: {
                    System.out.println("\nInvalid choice!\n");
                }
            }
            next();
        }

    }

    public static void next(){
        System.out.println();
        System.out.print("Press Enter to continue...");
        READER.nextLine();
        System.out.println();
    }

    public static void intro(){
        System.out.println("Ladies, gentlemen, and Mr. Cho...");
        next();
        System.out.println("Aadhya and Ken presents...");
        next();
        System.out.println("A dataset that will blow your minds!");
        next();
        System.out.println("Introducing.....");
        System.out.println("*insert drum roll effect here*");
        next();


    }

    public static void printMenu(){
        System.out.println("WIP");
    }

    public static void runTest() {
        MediaLibrary newLibrary = new MediaLibrary();
        currentList.add(2);
        currentList.add(9);
        currentList.add(1);
        currentList.add(10);
        currentList.add(-2);
        currentList.add(3);
        System.out.println(currentList);
        sortMediaBy(currentList);
        System.out.println(searchInt(1, currentList));
    }

    public static void sortMediaBy(ArrayList<Integer> currentList) {
        if (!(currentList.isEmpty() || currentList.size() == 1)) {
            sortMediaBy(0, currentList.size() - 1, currentList);
        }
    }

    public static void sortMediaBy(int start, int end, ArrayList<Integer> currentList) {
        if (start < end) {
            int pivot = currentList.get(start);
            int left = start;
            int right = end;

            while (left < right) {

                while (currentList.get(right).compareTo(pivot) >= 0 && left < right) {
                    right--;
                }
                currentList.set(left, currentList.get(right));

                while (currentList.get(left).compareTo(pivot) <= 0 && left < right) {
                    left++;
                }
                currentList.set(right, currentList.get(left));
            }
            System.out.println(currentList);
            currentList.set(left, pivot);
            sortMediaBy(start, left - 1, currentList);
            sortMediaBy(right + 1, end, currentList);
        }
    }

    public static int searchInt(int num, ArrayList<Integer> currentList) {
        int start = 0;
        int end = currentList.size() - 1;
        int midpoint;

        while (start <= end) {
            midpoint = (start + end) / 2;
            System.out.println(midpoint);
            if (currentList.get(midpoint) == num) {
                return currentList.get(midpoint);
            } else if (currentList.get(midpoint) < num) {
                start = midpoint + 1;
            } else {
                end = midpoint - 1;
            }
            System.out.println(start + " " + end);
        }
        return -1;
    }
}