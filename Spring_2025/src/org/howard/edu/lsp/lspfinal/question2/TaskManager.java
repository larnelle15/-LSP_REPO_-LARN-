package org.howard.edu.lsp.lspfinal.question2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of tasks, allowing addition, retrieval, and status updates
 */
public class TaskManager {
    private Map<String, Task> tasks;
    private final String[] VALID_STATUSES = {"TODO", "IN_PROGRESS", "DONE"};

    /**
     * Constructs a new TaskManager with an empty task collection
     */
    public TaskManager() {
        tasks = new HashMap<>();
    }

    /**
     * Adds a new task to the manager
     * 
     * @param name the unique name of the task
     * @param priority the priority of the task (lower number = higher priority)
     * @param status the status of the task (TODO, IN_PROGRESS, or DONE)
     * @throws DuplicateTaskException if a task with the same name already exists
     */
    public void addTask(String name, int priority, String status) throws DuplicateTaskException {
        if (tasks.containsKey(name)) {
            throw new DuplicateTaskException(name);
        }
        
        tasks.put(name, new Task(name, priority, status));
    }

    /**
     * Retrieves a task by its name
     * 
     * @param name the name of the task to retrieve
     * @return the task with the specified name
     * @throws TaskNotFoundException if no task with the specified name exists
     */
    public Task getTaskByName(String name) throws TaskNotFoundException {
        if (!tasks.containsKey(name)) {
            throw new TaskNotFoundException(name);
        }
        
        return tasks.get(name);
    }

    /**
     * Updates the status of an existing task
     * 
     * @param name the name of the task to update
     * @param newStatus the new status for the task
     * @throws TaskNotFoundException if no task with the specified name exists
     */
    public void updateStatus(String name, String newStatus) throws TaskNotFoundException {
        Task task = getTaskByName(name);
        task.setStatus(newStatus);
    }

    /**
     * Prints all tasks grouped by their status
     */
    public void printTasksGroupedByStatus() {
        System.out.println("Tasks grouped by status:");
        
        Map<String, List<Task>> tasksByStatus = new HashMap<>();
        
        // Initialize lists for each status
        for (String status : VALID_STATUSES) {
            tasksByStatus.put(status, new ArrayList<>());
        }
        
        // Group tasks by status
        for (Task task : tasks.values()) {
            String status = task.getStatus();
            if (tasksByStatus.containsKey(status)) {
                tasksByStatus.get(status).add(task);
            }
        }
        
        // Print tasks for each status
        for (String status : VALID_STATUSES) {
            List<Task> tasksWithStatus = tasksByStatus.get(status);
            System.out.println(status + ":");
            
            if (tasksWithStatus.isEmpty()) {
                continue;
            }
            
            for (Task task : tasksWithStatus) {
                System.out.println("  " + task);
            }
        }
    }
}