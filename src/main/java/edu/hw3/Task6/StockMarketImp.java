package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarketImp implements StockMarket {
    PriorityQueue<Stock> stockPriorityQueue;

    public StockMarketImp() {
        this.stockPriorityQueue = new PriorityQueue<>(new Comparator<Stock>() {
            @Override
            public int compare(Stock stock1, Stock stock2) {
                return Double.compare(stock2.price(), stock1.price());
            }
        });
    }

    public PriorityQueue<Stock> getStockPriorityQueue() {
        return stockPriorityQueue;
    }

    @Override
    public void add(Stock stock) {
        this.stockPriorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        this.stockPriorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockPriorityQueue.peek();
    }

    public boolean comparePriorityQueues(PriorityQueue<Stock> pq1, PriorityQueue<Stock> pq2) {
        if (pq1.size() != pq2.size()) {
            return false;
        }

        while (!pq1.isEmpty() && !pq2.isEmpty()) {
            Stock stock1 = pq1.poll();
            Stock stock2 = pq2.poll();

            if (!stock1.equals(stock2)) {
                return false;
            }
        }

        return true;
    }
}
