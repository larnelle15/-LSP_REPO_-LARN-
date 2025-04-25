package org.howard.edu.lsp.lspfinal.question3;

/**
 * Abstract base class that implements the Template Method pattern for report generation.
 * Defines the skeleton of the report generation algorithm, with specific steps
 * deferred to subclasses.
 */
public abstract class Report {
    
    /**
     * Template method that defines the algorithm for generating a report.
     * This method cannot be overridden by subclasses.
     */
    public final void generateReport() {
        loadData();
        formatData();
        printReport();
    }
    
    /**
     * Loads the data needed for the report.
     * This is a hook method that must be implemented by subclasses.
     */
    protected abstract void loadData();
    
    /**
     * Formats the loaded data for the report.
     * This is a hook method that must be implemented by subclasses.
     */
    protected abstract void formatData();
    
    /**
     * Prints the final formatted report.
     * This is a hook method that must be implemented by subclasses.
     */
    protected abstract void printReport();
}