import src.data.DataLoader;
import src.data.StockData;
import src.backtest.BacktestEngine;
import src.Strategy.MovingAverageStrategy;
import java.util.List;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File dataFolder = new File("C:\\Users\\John Zhang\\IdeaProjects\\TradeSimulator\\src\\data");
        List<String> stockFiles = List.of(dataFolder.list((dir, name) -> name.endsWith(".csv")));
        for (String stockFile : stockFiles) {
            String filePath = dataFolder.getPath() + "/" + stockFile;
            List<StockData> stockData = DataLoader.loadStockData(filePath);

            // Apply strategy and backtesting
            MovingAverageStrategy strategy = new MovingAverageStrategy(50, 200);
            BacktestEngine engine = new BacktestEngine(strategy, stockData, 10000);
            System.out.println("Backtesting " + stockFile + "...");
            engine.runBacktest();
            System.out.println();
        }
    }
}