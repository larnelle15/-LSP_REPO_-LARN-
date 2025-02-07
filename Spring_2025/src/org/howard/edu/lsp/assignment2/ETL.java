package org.howard.edu.lsp.assignment2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ETL {
    
    // List of strings that will hold the header
    private String[] headers = {"ProductID", "Name", "Price", "Category"};
    // Instantiating a list to hold all maps (each map is a row of data)
    List<Map<String, String>> dataList = new ArrayList<>();
    
    public static void main(String[] args) {
        // Instantiating ETL class
        ETL processor = new ETL();
        // Initialize sample data
        processor.initializeData();
        // Transform the data
        List<Map<String, String>> transformedData = processor.transformer(processor.dataList);
        // Display results
        processor.displayResults(transformedData);
    }
    
    /**
     * Initialize the sample data provided in the assignment
     */
    private void initializeData() {
        // Sample data as provided in the assignment
        String[][] data = {
            {"1", "Book", "12.99", "Education"},
            {"2", "Laptop", "999.99", "Electronics"},
            {"3", "Notebook", "2.49", "Stationery"},
            {"4", "Headphones", "199.99", "Electronics"},
            {"5", "Pencil", "0.99", "Stationery"},
            {"6", "Smartphone", "699.49", "Electronics"}
        };
        
        // Convert each row to a map and add to dataList
        for (String[] row : data) {
            mapToList(lineToMap(String.join(",", row), headers));
        }
    }
    
    /**
     * Function to convert each line to a map
     */
    public Map<String, String> lineToMap(String line, String[] headers) {
        String[] values = line.split(",");
        Map<String, String> rowMap = new LinkedHashMap<>();
        
        for (int i = 0; i < headers.length; i++) {
            rowMap.put(headers[i], values[i]);
        }
        return rowMap;
    }
    
    /**
     * Function to add each map to the list
     */
    public void mapToList(Map<String, String> map) {
        dataList.add(map);
    }
    
    /**
     * Function to perform all required transformations at once
     */
    public List<Map<String, String>> transformer(List<Map<String, String>> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> map = dataList.get(i);
            electronicsTransformer(map);
            caseChanger(map);
            priceRanger(map);
        }
        return dataList;
    }
    
    /**
     * Function to apply 10% discount to Electronics and 
     * change category of those over $500
     */
    public Map<String, String> electronicsTransformer(Map<String, String> map) {
        if (map.get("Category").equals("Electronics")) {
            double originalPrice = Double.parseDouble(map.get("Price"));
            double discountedPrice = originalPrice - (originalPrice * .1);
            BigDecimal roundedPrice = new BigDecimal(discountedPrice).setScale(2, RoundingMode.HALF_UP);
            map.put("Price", roundedPrice.toString());
            
            if (roundedPrice.doubleValue() > 500) {
                map.put("Category", "Premium Electronics");
            }
        }
        return map;
    }
    
    /**
     * Function to convert all product names to upper case
     */
    public Map<String, String> caseChanger(Map<String, String> map) {
        String lowerName = map.get("Name");
        String upperName = lowerName.toUpperCase();
        map.put("Name", upperName);
        return map;
    }
    
    /**
     * Function to assign a price range to all items
     */
    public Map<String, String> priceRanger(Map<String, String> map) {
        String stringPrice = map.get("Price");
        Double doublePrice = Double.parseDouble(stringPrice);
        
        if (doublePrice < 10.01) {
            map.put("PriceRange", "Low");
        } else if (doublePrice >= 10.01 && doublePrice < 100.01) {
            map.put("PriceRange", "Medium");
        } else if (doublePrice >= 100.01 && doublePrice < 500.01) {
            map.put("PriceRange", "High");
        } else {
            map.put("PriceRange", "Premium");
        }
        
        return map;
    }
    
    /**
     * Display the transformed results
     */
    private void displayResults(List<Map<String, String>> transformedData) {
        System.out.println("\nTransformed Data Results:");
        System.out.println("--------------------------");
        
        // Print headers including PriceRange
        System.out.printf("%-10s %-15s %-10s %-20s %-10s%n", 
            "ProductID", "Name", "Price", "Category", "PriceRange");
        System.out.println("----------------------------------------------------------");
        
        // Print each transformed record
        for (Map<String, String> row : transformedData) {
            System.out.printf("%-10s %-15s %-10s %-20s %-10s%n",
                row.get("ProductID"),
                row.get("Name"),
                row.get("Price"),
                row.get("Category"),
                row.get("PriceRange")
            );
        }
    }
}