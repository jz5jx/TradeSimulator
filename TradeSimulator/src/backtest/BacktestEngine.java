// File: backtest/BacktestEngine.java
package src.backtest;
import src.data.StockData;
import src.Strategy.Strategy;
import java.util.List;

public class BacktestEngine {
    private Strategy strategy;
    private List<StockData> stockData;
    private double initialBalance;

    public BacktestEngine(Strategy strategy, List<StockData> stockData, double initialBalance) {
        this.strategy = strategy;
        this.stockData = stockData;
        this.initialBalance = initialBalance;
    }

    public void runBacktest() {
        boolean hasPosition = false;
        double balance = initialBalance;
        double shares = 0;

        for (int i = 0; i < stockData.size(); i++) {
            StockData data = stockData.get(i);
            if (strategy.shouldBuy(stockData, i) && !hasPosition) {
                shares = balance / data.getClose();
                balance = 0;
                hasPosition = true;
                System.out.println("Buying on " + data.getDate() + " at price " + data.getClose());
            } else if (strategy.shouldSell(stockData, i) && hasPosition) {
                balance = shares * data.getClose();
                shares = 0;
                hasPosition = false;
                System.out.println("Selling on " + data.getDate() + " at price " + data.getClose());
            }
        }

        double finalBalance = balance + (shares * stockData.get(stockData.size() - 1).getClose());
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("Final Balance: " + finalBalance);
        System.out.println("Profit: " + (finalBalance - initialBalance));
    }
}
