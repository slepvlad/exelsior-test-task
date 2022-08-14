package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.Stock;
import ge.exelsior.code.challenge.model.dto.InitialData;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsoleOutputService implements OutputService {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String PRINT_STATISTIC_TEMPLATE =
            "\tTicker name = %s,\tdays = %s,\tmaximum price = %s,\tminimum price = %s%n";
    private static final String PRINT_MOVING_AVERAGE_TEMPLATE = "\t%s\t%s%n";

    private final StockService stockService;

    public ConsoleOutputService(StockService stockService) {
        this.stockService = stockService;
    }


    @Override
    public void printStatistic(List<Stock> stocks, InitialData initialData) {
        var days = initialData.days();
        var elements = initialData.elements();

        stocks
                .forEach(stock -> {
                    System.out.printf(PRINT_STATISTIC_TEMPLATE,
                            stock.getTicker().name(),
                            stock.getStatistics().size(),
                            stock.getAggregatedData().getMaximumPrice(),
                            stock.getAggregatedData().getMinimumPrice());
                    printMovingAverage(stock, days, elements);
                });
    }

    private void printMovingAverage(Stock stock, int days, int elements){
        var movingAverages = stockService.getMovingAverage(stock, days, elements);
        for(int i = movingAverages.size()-1; i >=0; i--) {
            System.out.printf(PRINT_MOVING_AVERAGE_TEMPLATE,
                    movingAverages.get(i).date().format(DATE_FORMATTER),
                    DECIMAL_FORMAT.format(movingAverages.get(i).average()));
        }
    }
}
