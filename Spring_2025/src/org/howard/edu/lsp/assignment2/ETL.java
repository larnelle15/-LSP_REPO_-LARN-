package org.howard.edu.lsp.assignment2;

//Importing required classes 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class ETL {
	
	public static void main(String[] args) {
		
		// Relative path to file
		String relativePath = "data/products.csv";
		String outputPath = "data/transformed_products.csv";
		// Instantiating ETL class
		ETL processor = new ETL();
		processor.reader(relativePath); // extraction: calling reader to extract file
		List<Map<String, String>> data = processor.transformer(processor.dataList); // transformation: creating a formatted list of maps to store data from .csv file
		processor.writeCSV(outputPath, data); // loading: calling writer to write new .csv file to data directory
	}
	
	// List of strings that will hold the header of the .csv file
	private String[] headers;
	// Instantiating a list to hold all maps (each map is a row of the .csv file)
	List<Map<String, String>> dataList = new ArrayList<>();
	
	
	// EXTRACT
	
	/***
	 * Function to read .csv file and convert each row to a map for
	 * easy storage within a list.
	 * @param fileName (the .csv file to be read)
	 */
	public void reader(String fileName) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line;
			boolean isFirstLine = true;
			while ((line = bufferedReader.readLine()) != null) { // Reads line by line until EOF
				if (line.isEmpty()) { // checks if line is empty
					continue; // if a line is empty, it is not printed
				}
				if (isFirstLine) { // runs on first line only to save header values
					headers = line.split(",");
					isFirstLine = false;
				} else { // runs on every other line of .csv besides the first
					mapToList(lineToMap(line, headers)); // converts the .csv line to a map and then adds that map to an array
				}
			}
		}
			
		catch (IOException e) { // Catches Input/Output error
			e.printStackTrace(); // Prints detailed error message
		}
	}
	
	// TRANSFORM
	
	/***
	 * Function to convert each line of the .csv to a map, where the header is k and the corresponding value under said header is v)
	 * @param line (the line of the .csv to be converted)
	 * @param headers (a list of strings of the .csv header fields)
	 * @return rowMap (a map of the .csv line)
	 */
	public Map<String, String> lineToMap(String line, String[] headers) { // function returning a hashMap from csv lines
		String[] values = line.split(","); // Splits .csv lines by commas
		Map<String, String> rowMap = new LinkedHashMap<>(); // Instantiating a new LinkedHashMap to store data
		
		for (int i = 0; i < headers.length; i++) {
			rowMap.put(headers[i], values[i]); // Maps column name to value
		}
		return rowMap;
	}
	
	/***
	 * Function to add each map of a row of the .csv to a list for easy navigation.
	 * @param map (the map of the row of the .csv file)
	 */
	public void mapToList(Map<String, String> map) {
		dataList.add(map);
	}
	
	/***
	 * Function to perform all required transformations at once.
	 * @param dataList (the list containing the maps of all lines of the original .csv)
	 * @return dataList (the new list after all the transformations)
	 */
	public List<Map<String, String>> transformer(List<Map<String, String>> dataList) {
		// Apply 10% discount to products in "Electronics" category
		for (int i = 0; i < dataList.size(); i++) { // iterates through each map in dataList
			Map<String, String> map = dataList.get(i);
			electronicsTransformer(map);
			caseChanger(map);
			priceRanger(map);
		}
		return dataList;
	}
	
	/***
	 * Function to apply 10% discount to Electronics and 
	 * change category of those over $500.
	 * @param map (a map of a single .csv line)
	 * @return map (new map of input .csv line with updated price/category)
	 */
	public Map<String, String> electronicsTransformer(Map<String, String> map) {
		if (map.get("Category").equals("Electronics")) {
			double originalPrice = Double.parseDouble(map.get("Price"));
			double discountedPrice = originalPrice - (originalPrice * .1); // Apply 10% discount
			BigDecimal roundedPrice = new BigDecimal(discountedPrice).setScale(2, RoundingMode.HALF_UP); // Rounding to nearest 100th (BigDecimal is more precise for rounding
			map.put("Price", roundedPrice.toString()); // Changing price in map
			
			// Changes Electronics > $500 to Premium Electronics
			if (roundedPrice.doubleValue() > 500) { 
				map.put("Category", "Premium Electronics");
			}
		}
		return map;
	}
	

	/***
	 * Function to convert all product names to upper case.
	 * @param map (a map of a single .csv line)
	 * @return map (new map with upper case product names)
	 */
	public Map<String, String> caseChanger(Map<String, String> map) {
		String lowerName = map.get("Name"); // store original lower case name
		String upperName = lowerName.toUpperCase(); // covert name to upper case
		map.put("Name", upperName); // update map
		
		return map;
	}
	
	/***
	 * Function to assign a price range to all items.
	 * Low: 0 - 10
	 * Med: 10.01 - 100
	 * High: 100.01 - 500
	 * Prem: 500.01+
	 * @param map (a map of a single .csv line)
	 * @return map (new map with updated PriceRange field)
	 */
	public Map<String, String> priceRanger(Map<String, String> map) {
		// Check price
		String stringPrice = map.get("Price");
		Double doublePrice = Double.parseDouble(stringPrice);
		// Price Range
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
	
	// LOAD
	
	/***
	 * Function to write transformed .csv file to the data directory.
	 * @param filePath (the output directory to store new .csv in)
	 * @param dataList (the list containing the maps of all lines of the original .csv)
	 */
	public void writeCSV(String filePath, List<Map<String, String>> dataList) {
		// Write header row
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			if (headers != null) { // create the header line of new .csv
				writer.write(String.join(",", headers));
				writer.newLine();
			}
			
			for (Map<String, String> row : dataList ) { // for each row in the dataList
				List<String> values = new ArrayList<>();
				for (String header : headers) {
					values.add(row.getOrDefault(header, ""));
				}
				writer.write(String.join(",", values));
				writer.newLine();
			}
			System.out.println("CSV file written successfully!");
		}
		catch (IOException e) {
			e.printStackTrace(); // prints detailed error message
		}
	}
}
