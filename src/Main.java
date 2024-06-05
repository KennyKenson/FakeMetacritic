public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        runTest();
        /*if (!isLocked) {
            review = "";
        } else {
            System.out.println("The selected media is currently locked. Unlock in order to modify anything. ");
        }*/
    }

    public static void runTest() {
        MediaLibrary newLibrary = new MediaLibrary();
        newLibrary.sortMediaByRating();
    }
}