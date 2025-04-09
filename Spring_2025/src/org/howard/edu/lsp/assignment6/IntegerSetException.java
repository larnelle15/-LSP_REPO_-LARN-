
package org.howard.edu.lsp.assignment6;

/**
 * Custom exception class for IntegerSet operations.
 * Thrown when operations are attempted on an empty set that require non-empty sets.
 * 
 * @author Larnelle Ankunda
 * @version 1.0
 * @since 2025-04-08
 */
public class IntegerSetException extends RuntimeException {
    
    /**
     * Constructs a new IntegerSetException with the specified detail message.
     * 
     * @param message The detail message
     */
    public IntegerSetException(String message) {
        super(message);
    }
}