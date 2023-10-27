package edu.hw3;

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
        Task6.StockMarketImp defaultStockMarket = new Task6.StockMarketImp();
        defaultStockMarket.add(new Task6.Stock("TCSG", 3692.2));
        defaultStockMarket.add(new Task6.Stock("FIVE", 2372.0));
        defaultStockMarket.add(new Task6.Stock("YNDX", 2682.4));

        return Stream.of(Arguments.of(defaultStockMarket));
    }
    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("add() test")
    void addTest(Task6.StockMarketImp defaultStockMarket) {
        Task6.StockMarketImp stockMarketImp = new Task6.StockMarketImp();
        stockMarketImp.add(new Task6.Stock("TCSG", 3692.2));
        stockMarketImp.add(new Task6.Stock("FIVE", 2372.0));
        stockMarketImp.add(new Task6.Stock("YNDX", 2682.4));

        assertTrue(stockMarketImp.equals(defaultStockMarket));
    }

    @ParameterizedTest
    @MethodSource("provideData")
    @DisplayName("remove() test")
    void removeTest(Task6.StockMarketImp defaultStockMarket) {
        Task6.StockMarketImp stockMarketImp = new Task6.StockMarketImp();
        stockMarketImp.add(new Task6.Stock("TCSG", 3692.2));
        stockMarketImp.add(new Task6.Stock("FIVE", 2372.0));
        stockMarketImp.add(new Task6.Stock("YNDX", 2682.4));

        stockMarketImp.add(new Task6.Stock("SBER", 269.9));
        stockMarketImp.remove(new Task6.Stock("SBER", 269.9));

        assertTrue(stockMarketImp.equals(defaultStockMarket));
    }

    @Test
    @DisplayName("mostValuableStock() test")
    void mostValuableStockTest() {
        Task6.Stock stock = new Task6.Stock("TCSG", 3692.2);

        Task6.StockMarketImp stockMarketImp = new Task6.StockMarketImp();
        stockMarketImp.add(stock);
        stockMarketImp.add(new Task6.Stock("FIVE", 2372.0));
        stockMarketImp.add(new Task6.Stock("YNDX", 2682.4));

        assertEquals(stockMarketImp.mostValuableStock(), stock);
    }
}
