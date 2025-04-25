package org.howard.edu.lsp.lspfinal.question3;

/**
 * Concrete implementation of the Report class for generating Inventory reports.
 */
public class InventoryReport extends Report {
    
    @Override
    protected void loadData() {
        System.out.println("Loading inventory data...");
        // Implementation would load inventory-specific data
    }
    
    @Override
    protected void formatData() {
        System.out.println("Formatting inventory data...");
        // Implementation would format inventory data for presentation
    }
    
    @Override
    protected void printReport() {
        System.out.println("Printing inventory report.");
        // Implementation would output the formatted inventory report
    }
}