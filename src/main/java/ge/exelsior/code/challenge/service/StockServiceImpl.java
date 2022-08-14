package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.HistoryDataStatistic;
import ge.exelsior.code.challenge.model.MovingAverage;
import ge.exelsior.code.challenge.model.Stock;
import ge.exelsior.code.challenge.model.dto.StockDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static ge.exelsior.code.challenge.mapper.StockMapper.createFromDto;
import static ge.exelsior.code.challenge.mapper.StockMapper.update;

public class StockServiceImpl implements StockService {

    public static final int PRECISION = 4;

    @Override
    public List<Stock> createStocks(List<StockDto> stockDtoList) {
        Map<String, Stock> stockMap = new HashMap<>();
        stockDtoList.forEach(dto -> {
                    var stock = Optional.ofNullable(stockMap.get(dto.getTickerName()))
                            .map(existedStock -> update(existedStock, createFromDto(dto)))
                            .orElse(createFromDto(dto));
                    stockMap.put(stock.getTicker().name(), stock);
                }
        );

        return stockMap.values()
                .stream()
                .toList();
    }

    @Override
    public List<MovingAverage> getMovingAverage(
            Stock stock,
            int days,
            int elements
    ) {
        validateMovingAverageIncomeData(stock, days, elements);

        stock.getStatistics().sort(Comparator.comparing(HistoryDataStatistic::getDate));
        List<MovingAverage> movingAverage = new ArrayList<>();
        int lastElementIndex = stock.getStatistics().size() - 1;
        BigDecimal sum = null;
        int i = 0;
        int j = 0;
        while (i < elements) {
            int finalJ = j;
            if (j < days) {
                sum = Optional.ofNullable(sum)
                        .map(item -> item.add(stock.getStatistics().get(lastElementIndex - finalJ).getPriceClose()))
                        .orElse(stock.getStatistics().get(lastElementIndex - j).getPriceClose());
                j++;
            } else {
                sum = sum
                        .add(stock.getStatistics().get(lastElementIndex - finalJ - i).getPriceClose())
                        .subtract(stock.getStatistics().get(lastElementIndex - i).getPriceClose());

                BigDecimal average = sum.divide(BigDecimal.valueOf(days), PRECISION, RoundingMode.HALF_UP);
                LocalDate date = stock.getStatistics().get(lastElementIndex - i).getDate();
                i++;
                movingAverage.add(new MovingAverage(date, average));
            }
        }
        return movingAverage;
    }

    private void validateMovingAverageIncomeData(Stock stock, int days, int elements){
        if (stock.getStatistics().size() < elements + days) {
            System.out.printf("Not enough data for computing moving average ticker = %s, m = %s, n = days Have %d elements in data %n",
                    stock.getTicker().name(),
                    elements,
                    days,
                    stock.getStatistics().size());
            throw new IllegalArgumentException("Wrong days(m) and elements(n) values");
        }
    }
}
