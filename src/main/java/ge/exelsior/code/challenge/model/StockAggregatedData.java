package ge.exelsior.code.challenge.model;

import java.math.BigDecimal;

public class StockAggregatedData {

    private BigDecimal maximumPrice;
    private BigDecimal minimumPrice;

    public BigDecimal getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(BigDecimal maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public BigDecimal getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(BigDecimal minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}
