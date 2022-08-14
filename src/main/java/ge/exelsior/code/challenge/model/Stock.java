package ge.exelsior.code.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private Ticker ticker;
    private List<HistoryDataStatistic> statistics = new ArrayList<>();

    public StockAggregatedData getAggregatedData() {
        return aggregatedData;
    }

    public void setAggregatedData(StockAggregatedData aggregatedData) {
        this.aggregatedData = aggregatedData;
    }

    private StockAggregatedData aggregatedData;


    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public List<HistoryDataStatistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<HistoryDataStatistic> statistics) {
        this.statistics = statistics;
    }
}
