package ge.exelsior.code.challenge.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class HistoryDataStatistic {

    private LocalDate date;
    private BigDecimal priceClose;
    private BigDecimal priceOpen;
    private BigDecimal priceHigh;
    private BigDecimal priceLow;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(BigDecimal priceClose) {
        this.priceClose = priceClose;
    }

    public BigDecimal getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(BigDecimal priceOpen) {
        this.priceOpen = priceOpen;
    }

    public BigDecimal getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(BigDecimal priceHigh) {
        this.priceHigh = priceHigh;
    }

    public BigDecimal getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(BigDecimal priceLow) {
        this.priceLow = priceLow;
    }
}
