package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test6 {
    static Stream<Arguments> provideData() {
        StockMarketImp defaultStockMarket = new StockMarketImp();
        defaultStockMarket.add(new Stock("TCSG", 3692.2));
        defaultStockMarket.add(new Stock("FIVE", 2372.0));
        defaultStockMarket.add(new Stock("YNDX", 2682.4));

        return Stream.of(Arguments.of(defaultStockMarket));
    }
    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("add() test")
    void addTest(StockMarketImp defaultStockMarket) {
        StockMarketImp stockMarketImp = new StockMarketImp();
        stockMarketImp.add(new Stock("TCSG", 3692.2));
        stockMarketImp.add(new Stock("FIVE", 2372.0));
        stockMarketImp.add(new Stock("YNDX", 2682.4));

        assertTrue(stockMarketImp.comparePriorityQueues(stockMarketImp.getStockPriorityQueue(), defaultStockMarket.getStockPriorityQueue()));
    }

    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("remove() test")
    void removeTest(StockMarketImp defaultStockMarket) {
        StockMarketImp stockMarketImp = new StockMarketImp();
        stockMarketImp.add(new Stock("TCSG", 3692.2));
        stockMarketImp.add(new Stock("FIVE", 2372.0));
        stockMarketImp.add(new Stock("YNDX", 2682.4));

        stockMarketImp.add(new Stock("SBER", 269.9));
        stockMarketImp.remove(new Stock("SBER", 269.9));

        assertTrue(stockMarketImp.comparePriorityQueues(stockMarketImp.getStockPriorityQueue(), defaultStockMarket.getStockPriorityQueue()));
    }

    @Test
    @DisplayName("mostValuableStock() test")
    void mostValuableStockTest() {
        Stock stock = new Stock("TCSG", 3692.2);

        StockMarketImp stockMarketImp = new StockMarketImp();
        stockMarketImp.add(stock);
        stockMarketImp.add(new Stock("FIVE", 2372.0));
        stockMarketImp.add(new Stock("YNDX", 2682.4));

        assertEquals(stockMarketImp.mostValuableStock(), stock);
    }
}
