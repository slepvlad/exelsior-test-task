package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.Stock;
import ge.exelsior.code.challenge.model.dto.StockDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockServiceImplTest {

    private static final StockService stockService = new StockServiceImpl();

    @Test
    void createStocksSuccess() {

        List<StockDto> dtos = generateStockDtos("AMAZON", 3);
        List<Stock> stocks = stockService.createStocks(dtos);
        assertEquals(1, stocks.size());
        var stock = stocks.get(0);
        assertEquals("AMAZON", stock.getTicker().name());
        assertEquals(
                new BigDecimal("5.9525"),
                stock.getAggregatedData().getMaximumPrice()
        );
        assertEquals(
                new BigDecimal("0.75"),
                stock.getAggregatedData().getMinimumPrice()
        );

        assertEquals(3, stock.getStatistics().size());

        assertEquals(LocalDate.parse("2022-08-10"), stock.getStatistics().get(0).getDate());
        assertEquals(
                new BigDecimal("1.5"),
                stock.getStatistics().get(0).getPriceClose()
        );
        assertEquals(
                new BigDecimal("2.44245"),
                stock.getStatistics().get(0).getPriceOpen()
        );
        assertEquals(
                new BigDecimal("3.9525"),
                stock.getStatistics().get(0).getPriceHigh()
        );
        assertEquals(
                new BigDecimal("0.75"),
                stock.getStatistics().get(0).getPriceLow()
        );

        assertEquals(LocalDate.parse("2022-08-09"), stock.getStatistics().get(1).getDate());
        assertEquals(
                new BigDecimal("2.5"),
                stock.getStatistics().get(1).getPriceClose()
        );
        assertEquals(
                new BigDecimal("3.44245"),
                stock.getStatistics().get(1).getPriceOpen()
        );
        assertEquals(
                new BigDecimal("4.9525"),
                stock.getStatistics().get(1).getPriceHigh()
        );
        assertEquals(
                new BigDecimal("1.75"),
                stock.getStatistics().get(1).getPriceLow()
        );

        assertEquals(LocalDate.parse("2022-08-08"), stock.getStatistics().get(2).getDate());
        assertEquals(
                new BigDecimal("3.5"),
                stock.getStatistics().get(2).getPriceClose()
        );
        assertEquals(
                new BigDecimal("4.44245"),
                stock.getStatistics().get(2).getPriceOpen()
        );
        assertEquals(
                new BigDecimal("5.9525"),
                stock.getStatistics().get(2).getPriceHigh()
        );
        assertEquals(
                new BigDecimal("2.75"),
                stock.getStatistics().get(2).getPriceLow()
        );
    }

    @Test
    void createTwoStocksSuccess() {

        List<StockDto> amazonDtos = generateStockDtos("AMAZON", 3);
        List<StockDto> googleDtos = generateStockDtos("GOOG", 10);

        List<StockDto> aggregatedDtos = new ArrayList<>(amazonDtos);
        aggregatedDtos.addAll(googleDtos);
        List<Stock> stocks = new ArrayList<>(stockService.createStocks(aggregatedDtos));
        stocks.sort(Comparator.comparing((Stock o) -> o.getTicker().name()));
        assertEquals(2, stocks.size());
        assertEquals("AMAZON", stocks.get(0).getTicker().name());
        assertEquals(3, stocks.get(0).getStatistics().size());
        assertEquals("GOOG", stocks.get(1).getTicker().name());
        assertEquals(10, stocks.get(1).getStatistics().size());
    }


    @Test
    void getMovingAverageSuccess() {
        List<StockDto> dtos = generateStockDtos("AMAZON", 15);
        List<Stock> stocks = stockService.createStocks(dtos);

        var movingAverages = stockService.getMovingAverage(stocks.get(0), 5, 3);
        assertEquals(3, movingAverages.size());

        assertEquals(LocalDate.parse("2022-07-27"), movingAverages.get(2).date());
        assertEquals(new BigDecimal("6.5000"), movingAverages.get(2).average());

        assertEquals(LocalDate.parse("2022-07-28"), movingAverages.get(1).date());
        assertEquals(new BigDecimal("5.5000"), movingAverages.get(1).average());

        assertEquals(LocalDate.parse("2022-07-29"), movingAverages.get(0).date());
        assertEquals(new BigDecimal("4.5000"), movingAverages.get(0).average());
    }

    @Test
    void getMovingAverageSuccessElementsMoreThanDays() {
        List<StockDto> dtos = generateStockDtos("AMAZON", 8);
        List<Stock> stocks = stockService.createStocks(dtos);

        var movingAverages = stockService.getMovingAverage(stocks.get(0), 3, 5);
        assertEquals(5, movingAverages.size());

        assertEquals(LocalDate.parse("2022-08-05"), movingAverages.get(0).date());
        assertEquals(new BigDecimal("3.5000"), movingAverages.get(0).average());

        assertEquals(LocalDate.parse("2022-08-04"), movingAverages.get(1).date());
        assertEquals(new BigDecimal("4.5000"), movingAverages.get(1).average());

        assertEquals(LocalDate.parse("2022-08-03"), movingAverages.get(2).date());
        assertEquals(new BigDecimal("5.5000"), movingAverages.get(2).average());

        assertEquals(LocalDate.parse("2022-08-02"), movingAverages.get(3).date());
        assertEquals(new BigDecimal("6.5000"), movingAverages.get(3).average());

        assertEquals(LocalDate.parse("2022-08-01"), movingAverages.get(4).date());
        assertEquals(new BigDecimal("7.5000"), movingAverages.get(4).average());
    }

    @Test
    void getMovingAverageFail() {
        List<StockDto> dtos = generateStockDtos("AMAZON", 7);
        List<Stock> stocks = stockService.createStocks(dtos);

        assertThrows(
                IllegalArgumentException.class,
                () -> stockService.getMovingAverage(stocks.get(0), 3, 5)
        );
    }

    private List<StockDto> generateStockDtos(String tickerName, int days) {
        LocalDate startDate = LocalDate.parse("2022-08-13").minusDays(days);
        String startLowPrice = "0.75";
        String  startHighPrice = "3.9525";
        String  priceOpen = "2.44245";
        String priceClose = "1.5";
        List<StockDto> result = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            result.add(StockDto.builder()
                    .tickerName(tickerName)
                    .priceLow(new BigDecimal(startLowPrice).add(BigDecimal.valueOf(i)))
                    .priceHigh(new BigDecimal(startHighPrice).add(BigDecimal.valueOf(i)))
                    .priceOpen(new BigDecimal(priceOpen).add(BigDecimal.valueOf(i)))
                    .priceClose(new BigDecimal(priceClose).add(BigDecimal.valueOf(i)))
                    .date(startDate.minusDays(i))
                    .build()
            );
        }
        return result;
    }
}