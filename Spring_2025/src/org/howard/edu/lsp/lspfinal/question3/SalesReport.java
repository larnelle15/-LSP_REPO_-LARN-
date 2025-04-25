package org.howard.edu.lsp.lspfinal.question3;

/**
 * Concrete implementation of the Report class for generating Sales reports.
 */
public class SalesReport extends Report {
    
    @Override
    protected void loadData() {
        System.out.println("Loading sales data...");
        // Implementation would load sales-specific data
    }
    
    @Override
    protected void formatData() {
        System.out.println("Formatting sales data...");
        // Implementation would format sales data for presentation
    }
    
    @Override
    protected void printReport() {
        System.out.println("Printing sales report.");
        // Implementation would output the formatted sales report
    }
}
