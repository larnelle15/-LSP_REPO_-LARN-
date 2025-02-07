package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ETL {
    
    // List of strings that will hold the header of the .csv file
    private String[] headers;
    // Instantiating a list to hold all maps (each map is a row of the .csv file)
    List<Map<String, String>> dataList = new ArrayList<>();
    
    public static void main(String[] args) {
        // Creating data directory if it doesn't exist
        new File("data").mkdirs();
        
        // Create the input CSV file with sample data
        createSampleFile("data/products.csv");
        
        // Relative path to file
        String relativePath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";
        
        // Instantiating ETL class
        ETL processor = new ETL();
        processor.reader(relativePath); // extraction: calling reader to extract file
        List<Map<String, String>> data = processor.transformer(processor.dataList); // transformation
        processor.writeCSV(outputPath, data); // loading: writing transformed data
    }
    
    /**
     * Creates the sample input file with the provided data
     */
    private static void createSampleFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("ProductID,Name,Price,Category");
            writer.println("1,Book,12.99,Education");
            writer.println("2,Laptop,999.99,Electronics");
            writer.println("3,Notebook,2.49,Stationery");
            writer.println("4,Headphones,199.99,Electronics");
            writer.println("5,Pencil,0.99,Stationery");
            writer.println("6,Smartphone,699.49,Electronics");
            System.out.println("Sample file created successfully at: " + fileName);
        } catch (IOException e) {
            System.err.println("Error creating sample file: " + e.getMessage());
        }
    }
    
    /**
     * Function to read .csv file and convert each row to a map for
     * easy storage within a list.
     * @param fileName the .csv file to be read
     */
    public void reader(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                if (isFirstLine) {
                    headers = line.split(",");
                    isFirstLine = false;
                } else {
                    mapToList(lineToMap(line, headers));
                }
            }
            System.out.println("Successfully read data from: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Function to convert each line of the .csv to a map
     * @param line the line of the .csv to be converted
     * @param headers list of strings of the .csv header fields
     * @return rowMap a map of the .csv line
     */
    public Map<String, String> lineToMap(String line, String[] headers) {
        String[] values = line.split(",");
        Map<String, String> rowMap = new LinkedHashMap<>();
        
        for (int i = 0; i < headers.length; i++) {
            rowMap.put(headers[i].trim(), values[i].trim());
        }
        return rowMap;
    }
    
    /**
     * Function to add each map of a row of the .csv to a list
     * @param map the map of the row of the .csv file
     */
    public void mapToList(Map<String, String> map) {
        dataList.add(map);
    }
    
    /**
     * Function to perform all required transformations at once
     * @param dataList the list containing the maps of all lines
     * @return dataList the transformed list
     */
    public List<Map<String, String>> transformer(List<Map<String, String>> dataList) {
        System.out.println("Applying transformations...");
        for (Map<String, String> map : dataList) {
            electronicsTransformer(map);
            caseChanger(map);
            priceRanger(map);
        }
        return dataList;
    }
    
    /**
     * Function to apply 10% discount to Electronics and 
     * change category of those over $500
     * @param map a map of a single .csv line
     * @return map transformed map
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
     * @param map a map of a single .csv line
     * @return map transformed map
     */
    public Map<String, String> caseChanger(Map<String, String> map) {
        String lowerName = map.get("Name");
        String upperName = lowerName.toUpperCase();
        map.put("Name", upperName);
        return map;
    }
    
    /**
     * Function to assign a price range to all items
     * @param map a map of a single .csv line
     * @return map transformed map
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
     * Function to write transformed .csv file
     * @param filePath the output directory to store new .csv
     * @param dataList the list containing the transformed data
     */
    public void writeCSV(String filePath, List<Map<String, String>> dataList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header including PriceRange
            List<String> headersList = new ArrayList<>();
            for (String header : headers) {
                headersList.add(header);
            }
            headersList.add("PriceRange");
            writer.write(String.join(",", headersList));
            writer.newLine();
            
            // Write transformed data
            for (Map<String, String> row : dataList) {
                List<String> values = new ArrayList<>();
                for (String header : headersList) {
                    values.add(row.getOrDefault(header, ""));
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }
            System.out.println("Successfully wrote transformed data to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
