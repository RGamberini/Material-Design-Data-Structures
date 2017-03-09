package edu.sunysuffolk.cst246;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

/**
 * The UnsortedOptimizedArray is mostly as the name implies an unsorted array, however it has two tricks:
 * First when the array is filled it automatically increases it's capacity by a factor of 1.5 (adjustable).
 * Second when the a Listing is fetched from the array it rises towards the top of the array allowing for faster fetch times on average.
 *
 * In addition this UnsortedOptimizedArray keeps as mirror of it's contents in an object called ObservableList so it can be easily shown through JavaFX.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class UnsortedOptimizedArray {
    private final float EXPANSIONFACTOR = .5f;
    private int next, size;
    private ProfessorListing[] data;
    public ObservableList<ProfessorListing> observableList;

    /**
     * The default constructor of an UnsortedOptimizedArray calls it's one argument constructor with a size of 100.
     */
    public UnsortedOptimizedArray() {
        this(100);
    }

    /**
     * The one argument constructor of an UnsortedOptimizedArray creates an empty array of size length.
     * @param size
     */
    public UnsortedOptimizedArray(int size) {
        this.next = 0;
        this.size = size;
        this.data = new ProfessorListing[size];
        this.observableList = FXCollections.observableArrayList();
    }

    /**
     * The insert method adds the new listing to the array at the end
     * @param newProfessorListing the new listing to be added.
     * @return true if successful false if not
     */
    public boolean insert(ProfessorListing newProfessorListing) {
        if (next >= size) expandData();
        data[next] = newProfessorListing.deepCopy();
        observableList.add(next , newProfessorListing);

        if (data[next] == null)
            return false;
        next++;
        return true;
    }

    /**
     * The sequential search method iterates though the array calling compareTo on every listing until finding a match.
     * @param targetKey the key that's compared to.
     * @return if successful the index of the matching listing otherwise returns next.
     */
    private int sequentialSearch(String targetKey) {
        int i = 0;
        while(i < next && data[i].compareTo(targetKey) != 0) i++;
        return i;
    }

    /**
     * The expandData method increases the size of the array by EXPANSIONFACTOR + 1
     */
    private void expandData() {
        ProfessorListing[] newData = new ProfessorListing[(int) ((float) data.length * (1 + EXPANSIONFACTOR))];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    /**
     * The fetch method looks through the array searching for a listing with a key-field matching the targetKey.
     * If it finds one it returns that listing but not before bumping it up one index
     * Otherwise the method returns null;
     * @param targetKey the key that's compared to.
     * @return the matching listing otherwise null.
     */
    public ProfessorListing fetch(String targetKey) {
        int i = sequentialSearch(targetKey);
        if (i == next)
            return null;
        ProfessorListing professorListing = data[i].deepCopy();
        if (i != 0) {
            ProfessorListing temp = data[i - 1];

            data[i - 1] = data[i];
            observableList.set(i - 1, data[i]);

            data[i] = temp;
            observableList.set(i, temp);
        }
        return professorListing;
    }

    /**
     * The delete method works by removing the listing (if found) matching targetKey and then swapping the highest index listing into it's palce.
     * @param targetKey the key that's compared to.
     * @return true if successful otherwise false.
     */
    public boolean delete(String targetKey) {
        int i = sequentialSearch(targetKey);
        if (i == next)
            return false;
        data[i] = data[next - 1];
        observableList.set(i, data[next - 1]);

        data[next - 1] = null;
        observableList.set(next - 1, null);
        next--;
        return true;
    }

    /**
     * The update method calls first delete on the original listing and then insert with the new listing.
     * @param targetKey the key that's compared to.
     * @param newProfessorListing the new listing
     * @return true if successful otherwise false.
     */
    public boolean update(String targetKey, ProfessorListing newProfessorListing) {
        return delete(targetKey) && insert(newProfessorListing);
    }

    /**
     * The showAll method prints everything contained in the array into standard I/O
     */
    public void showAll() {
        Arrays.stream(data).forEach(System.out::println);
    }
}
