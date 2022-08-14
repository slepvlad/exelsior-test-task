package ge.exelsior.code.challenge.mapper;

import ge.exelsior.code.challenge.model.HistoryDataStatistic;
import ge.exelsior.code.challenge.model.Stock;
import ge.exelsior.code.challenge.model.StockAggregatedData;
import ge.exelsior.code.challenge.model.Ticker;
import ge.exelsior.code.challenge.model.dto.StockDto;

import java.math.BigDecimal;

public class StockMapper {

    public static Stock createFromDto(StockDto stockDto) {
        var stock = new Stock();
        stock.setTicker(new Ticker(stockDto.getTickerName()));

        var historyStatistic = createHistoryDataStatisticFromDto(stockDto);
        stock.getStatistics().add(historyStatistic);

        var aggregatedData = getStockAggregatedData(stockDto.getPriceHigh(), stockDto.getPriceLow());
        stock.setAggregatedData(aggregatedData);
        return stock;
    }

    private static HistoryDataStatistic createHistoryDataStatisticFromDto(StockDto stockDto) {
        var historyStatistic = new HistoryDataStatistic();
        historyStatistic.setDate(stockDto.getDate());
        historyStatistic.setPriceClose(stockDto.getPriceClose());
        historyStatistic.setPriceOpen(stockDto.getPriceOpen());
        historyStatistic.setPriceHigh(stockDto.getPriceHigh());
        historyStatistic.setPriceLow(stockDto.getPriceLow());
        return historyStatistic;
    }

    public static Stock update(Stock target, Stock source) {
        var stock = new Stock();
        stock.setTicker(target.getTicker());
        stock.getStatistics().addAll(target.getStatistics());
        stock.getStatistics().addAll(source.getStatistics());

        var maxPrice = target.getAggregatedData().getMaximumPrice()
                .max(source.getAggregatedData().getMaximumPrice());
        var minPrice = target.getAggregatedData().getMinimumPrice()
                .min(source.getAggregatedData().getMinimumPrice());

        var aggregatedData = getStockAggregatedData(maxPrice, minPrice);

        stock.setAggregatedData(aggregatedData);
        return stock;
    }

    private static StockAggregatedData getStockAggregatedData(
            BigDecimal maxPrice,
            BigDecimal minPrice
    ) {
        var aggregatedData = new StockAggregatedData();
        aggregatedData.setMaximumPrice(maxPrice);
        aggregatedData.setMinimumPrice(minPrice);
        return aggregatedData;
    }
}
