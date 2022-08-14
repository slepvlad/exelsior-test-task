package ge.exelsior.code.challenge.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockDto {

    private final String tickerName;
    private final LocalDate date;
    private final BigDecimal priceClose;
    private final BigDecimal priceOpen;
    private final BigDecimal priceHigh;
    private final BigDecimal priceLow;

    private StockDto(Builder builder) {
        this.tickerName = builder.tickerName;
        this.date = builder.date;
        this.priceClose = builder.priceClose;
        this.priceOpen = builder.priceOpen;
        this.priceHigh = builder.priceHigh;
        this.priceLow = builder.priceLow;
    }

    public String getTickerName() {
        return tickerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPriceClose() {
        return priceClose;
    }

    public BigDecimal getPriceOpen() {
        return priceOpen;
    }

    public BigDecimal getPriceHigh() {
        return priceHigh;
    }

    public BigDecimal getPriceLow() {
        return priceLow;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String tickerName;
        private LocalDate date;
        private BigDecimal priceClose;
        private BigDecimal priceOpen;
        private BigDecimal priceHigh;
        private BigDecimal priceLow;

        private Builder() {
        }

        public Builder tickerName(String tickerName) {
            this.tickerName = tickerName;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder priceClose(BigDecimal priceClose) {
            this.priceClose = priceClose;
            return this;
        }

        public Builder priceOpen(BigDecimal priceOpen) {
            this.priceOpen = priceOpen;
            return this;
        }

        public Builder priceHigh(BigDecimal priceHigh) {
            this.priceHigh = priceHigh;
            return this;
        }

        public Builder priceLow(BigDecimal priceLow) {
            this.priceLow = priceLow;
            return this;
        }

        public StockDto build() {
            return new StockDto(this);
        }
    }
}
