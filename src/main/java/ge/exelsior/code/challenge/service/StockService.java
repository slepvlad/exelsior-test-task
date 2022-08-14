package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.MovingAverage;
import ge.exelsior.code.challenge.model.Stock;
import ge.exelsior.code.challenge.model.dto.StockDto;

import java.util.List;

public interface StockService {

    List<Stock> createStocks(List<StockDto> stockDtoList);

    List<MovingAverage> getMovingAverage(
            Stock stock,
            int days,
            int elements
    );
}
