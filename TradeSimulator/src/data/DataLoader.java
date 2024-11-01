// File: data/DataLoader.java
package src.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    // Method to load stock data from a CSV file
    public static List<StockData> loadStockData(String filePath) {
        List<StockData> stockDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header if there is one

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assumes CSV is comma-separated

                LocalDate date = LocalDate.parse(values[0]); // Parse date in format YYYY-MM-DD
                double open = Double.parseDouble(values[1]);
                double high = Double.parseDouble(values[2]);
                double low = Double.parseDouble(values[3]);
                double close = Double.parseDouble(values[4]);
                double volume = Double.parseDouble(values[5]);

                // Create StockData object and add to the list
                StockData stockData = new StockData(date, open, high, low, close, volume);
                stockDataList.add(stockData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockDataList;
    }
}
