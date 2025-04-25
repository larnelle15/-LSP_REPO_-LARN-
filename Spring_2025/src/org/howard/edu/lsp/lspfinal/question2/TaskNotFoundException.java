package org.howard.edu.lsp.lspfinal.question2;


/**
 * Exception thrown when attempting to access a task that does not exist
 */
public class TaskNotFoundException extends Exception {
    /**
     * Constructs a new TaskNotFoundException with the specified task name
     * 
     * @param taskName the name of the task that was not found
     */
    public TaskNotFoundException(String taskName) {
        super("Task '" + taskName + "' not found.");
    }
}
