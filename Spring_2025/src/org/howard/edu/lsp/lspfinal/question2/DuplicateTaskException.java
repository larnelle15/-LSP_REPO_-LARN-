package org.howard.edu.lsp.lspfinal.question2;

/**
 * Exception thrown when attempting to add a task with a name that already exists
 */
public class DuplicateTaskException extends Exception {
    /**
     * Constructs a new DuplicateTaskException with the specified task name
     * 
     * @param taskName the name of the duplicate task
     */
    public DuplicateTaskException(String taskName) {
        super("Task '" + taskName + "' already exists.");
    }
}