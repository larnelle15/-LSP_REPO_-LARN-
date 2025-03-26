package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a custom set of integers with various set operations.
 * This class provides methods to manipulate and analyze a collection of unique integers.
 * 
 * @author Larnelle Ankunda
 * @version 1.0
 * @since 2025-03-25
 */
public class IntegerSet {
    /** Internal list to store unique integer elements. */
    private List<Integer> set = new ArrayList<>();

    /**
     * Constructs an empty IntegerSet.
     */
    public IntegerSet() {}

    /**
     * Constructs an IntegerSet from an existing ArrayList of integers.
     * Removes duplicate values during initialization.
     * 
     * @param set The initial list of integers to create the set from
     */
    public IntegerSet(ArrayList<Integer> set) {
        this.set = set.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return The count of unique elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Compares this set with another object for equality.
     * Two sets are considered equal if they contain the same elements.
     * 
     * @param o The object to compare with this set
     * @return true if the sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IntegerSet)) return false;
        IntegerSet other = (IntegerSet) o;
        return set.size() == other.set.size() && 
               set.stream().allMatch(other.set::contains);
    }

    /**
     * Checks if the set contains a specific integer value.
     * 
     * @param value The integer to search for in the set
     * @return true if the value is in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Finds the largest element in the set.
     * 
     * @return The largest integer in the set
     * @throws RuntimeException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return set.stream().max(Integer::compare).get();
    }

    /**
     * Finds the smallest element in the set.
     * 
     * @return The smallest integer in the set
     * @throws RuntimeException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return set.stream().min(Integer::compare).get();
    }

    /**
     * Adds an item to the set if it's not already present.
     * 
     * @param item The integer to add to the set
     */
    public void add(int item) {
        if (!set.contains(item)) set.add(item);
    }

    /**
     * Removes a specific item from the set if it exists.
     * 
     * @param item The integer to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Performs a set union operation, adding all elements from another set.
     * 
     * @param intSetb The set to union with the current set
     */
    public void union(IntegerSet intSetb) {
        intSetb.set.forEach(this::add);
    }

    /**
     * Performs a set intersection operation, keeping only common elements.
     * 
     * @param intSetb The set to intersect with the current set
     */
    public void intersect(IntegerSet intSetb) {
        set.retainAll(intSetb.set);
    }

    /**
     * Performs a set difference operation, removing elements of another set.
     * 
     * @param intSetb The set to subtract from the current set
     */
    public void diff(IntegerSet intSetb) {
        set.removeAll(intSetb.set);
    }

    /**
     * Performs a set complement operation, keeping elements not in another set.
     * 
     * @param intSetb The set to use for complement operation
     */
    public void complement(IntegerSet intSetb) {
        set = set.stream()
            .filter(x -> !intSetb.contains(x))
            .collect(Collectors.toList());
    }

    /**
     * Checks if the set is empty.
     * 
     * @return true if the set contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set.
     * 
     * @return A string showing the set's contents
     */
    @Override
    public String toString() {
        return set.toString();
    }
}