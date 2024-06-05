import java.util.ArrayList;
public class Main {
    static ArrayList<Integer> currentList = new ArrayList<>();
    public static void main(String[] args) {
        runTest();
        /*if (!isLocked) {
            review = "";
        } else {
            System.out.println("The selected media is currently locked. Unlock in order to modify anything. ");
        }*/
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
            sortMediaBy(0, currentList.size()-1, currentList);
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
            sortMediaBy(start, left-1, currentList);
            sortMediaBy(right+1, end, currentList);
        }
    }

    public static int searchInt(int num, ArrayList<Integer> currentList) {
        int start = 0;
        int end = currentList.size()-1;
        int midpoint;

        while (start <= end) {
            midpoint = (start+end)/2;
            System.out.println(midpoint);
            if (currentList.get(midpoint) == num) {
                return currentList.get(midpoint);
            } else if (currentList.get(midpoint) < num) {
                start = midpoint+1;
            } else {
                end = midpoint-1;
            }
            System.out.println(start + " " + end);
        }
        return -1;
    }
}