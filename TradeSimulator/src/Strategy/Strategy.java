// File: strategy/Strategy.java
package src.Strategy;
import src.data.StockData;
import java.util.List;

public interface Strategy {
    boolean shouldBuy(List<StockData> stockData, int index);
    boolean shouldSell(List<StockData> stockData, int index);
}
