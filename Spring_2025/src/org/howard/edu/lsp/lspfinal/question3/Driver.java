package org.howard.edu.lsp.lspfinal.question3;

/**
 * Driver class to demonstrate the Report generation using the Template Method pattern.
 */
public class Driver {
    
    public static void main(String[] args) {
        // Create and generate a Sales report
        Report salesReport = new SalesReport();
        salesReport.generateReport();
        
        System.out.println(); // Empty line for readability
        
        // Create and generate an Inventory report
        Report inventoryReport = new InventoryReport();
        inventoryReport.generateReport();
    }
}
