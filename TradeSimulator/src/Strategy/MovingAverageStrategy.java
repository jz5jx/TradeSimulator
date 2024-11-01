// File: strategy/MovingAverageStrategy.java
package src.Strategy;
import src.data.StockData;
import java.util.List;

public class MovingAverageStrategy implements Strategy {
    private int shortWindow;
    private int longWindow;

    public MovingAverageStrategy(int shortWindow, int longWindow) {
        this.shortWindow = shortWindow;
        this.longWindow = longWindow;
    }

    @Override
    public boolean shouldBuy(List<StockData> stockData, int index) {
        if (index < longWindow) return false;
        double shortAvg = calculateMovingAverage(stockData, index, shortWindow);
        double longAvg = calculateMovingAverage(stockData, index, longWindow);
        return shortAvg > longAvg;
    }

    @Override
    public boolean shouldSell(List<StockData> stockData, int index) {
        if (index < longWindow) return false;
        double shortAvg = calculateMovingAverage(stockData, index, shortWindow);
        double longAvg = calculateMovingAverage(stockData, index, longWindow);
        return shortAvg < longAvg;
    }

    private double calculateMovingAverage(List<StockData> stockData, int endIndex, int window) {
        double sum = 0;
        for (int i = endIndex - window + 1; i <= endIndex; i++) {
            sum += stockData.get(i).getClose();
        }
        return sum / window;
    }
}
