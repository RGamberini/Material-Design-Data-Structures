package edu.sunysuffolk.cst246;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * The SortedArray is an array that keeps its contents alphabetically sorted by their key-value.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class SortedArray {
    private int size;
    private int next;
    private StudentListing[] data;
    private final StudentListing[] defaultListings = new StudentListing[]{
            new StudentListing("Rudy", "11102228", 4.0f),
            new StudentListing("Carl", "11002853", 3.8f),
            new StudentListing("Michael", "1101375", 2.1f),
            new StudentListing("John", "11001493", 3.0f)
    };

    /**
     * The default constructor of an SortedArray calls it's one argument constructor with a size of 100.
     */
    public SortedArray() {
        this(100);
    }

    /**
     * The one argument constructor of an SortedArray creates an array of size length.
     * And pre-populates as many listings as it can (up to 4)
     * @param size the size of the SortedArray
     */
    public SortedArray(int size) {
        this.size = size;
        this.data = new StudentListing[size];
        this.next = 0;

        for (int i = 0; i < Math.min(size, defaultListings.length); i++) {
            insert(defaultListings[i]);
        }
    }

    /**
     * Executes a binary search for the targetKey
     * @param targetKey the key to be searched for, does not have to be in the data array.
     * @return If the targetKey is found then it's index in data otherwise returns where it should be in data
     */
    public int binarySearch(String targetKey) {
        int low = 0;
        int high = next - 1;
        int i = (high - low) / 2;
        while (high - low > 0) {
            if (data[i].compareTo(targetKey) > 0) {
                high = i - 1;
            } else if (data[i].compareTo(targetKey) < 0) {
                low = i + 1;
            }
            i = (high + low) / 2;
        }
        if (data[i] != null && data[i].compareTo(targetKey) < 0)
            i++;
        return i;
    }

    /**
     * The insert method adds the new listing to the array at the correct sorted position.
     * @param newStudentListing the new listing to be added.
     * @return true if successful false if not
     */
    public boolean insert(StudentListing newStudentListing) {
        if (next + 1 == size) return false;
        int i = binarySearch(newStudentListing.getName());
        System.arraycopy(data, i, data, i+1, next - i);
        data[i] = newStudentListing.deepCopy();
        next++;
        return true;
    }

    /**
     * The fetch method looks through the array searching for a listing with a key-field matching the targetKey.
     * If it finds one it returns a deep copy of it.
     * Otherwise the method returns null.
     * @param targetKey the key that's compared to.
     * @return the matching listing otherwise null.
     */
    public StudentListing fetch(String targetKey) {
        int i = binarySearch(targetKey);
        if (!data[i].getName().equals(targetKey))
            return null;
        return data[i].deepCopy();
    }

    /**
     * The delete method works by finding the index of the target key and then shifting the array to take it's place
     * @param targetKey the key that's compared to.
     * @return true if successful otherwise false.
     */
    public boolean delete(String targetKey) {
        int i = binarySearch(targetKey);
        if (!data[i].getName().equals(targetKey))
            return false;
        System.arraycopy(data, i + 1, data, i, next - i);
        next--;
        data[next] = null;
        return true;
    }

    /**
     * The update method calls first delete on the original listing and then insert with the new listing.
     * @param targetKey the key that's compared to.
     * @param newStudentListing the new listing
     * @return true if successful otherwise false.
     */
    public boolean update(String targetKey, StudentListing newStudentListing) {
        return delete(targetKey) && insert(newStudentListing);
    }

    /**
     * The showAll method concatenates all of the StudentListings inside the SortedArray as Strings and returns them.
     * @return All of the StudentListings inside the SortedArray as Strings
     */
    public String showAll() {
        StringBuilder stringBuilder = new StringBuilder(next);
        Arrays.stream(data).forEach((student) -> {
            if (student != null)
                stringBuilder.append(student).append("\n");
        });
        return stringBuilder.toString();
    }
}
