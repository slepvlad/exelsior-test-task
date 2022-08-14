package ge.exelsior.code.challenge.mapper;

import ge.exelsior.code.challenge.model.dto.StockDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockDtoMapper {

    private static final String DATE_PATTERN = "M/d/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static StockDto createStockDto(String[] fields) {
        return StockDto.builder()
                .tickerName(fields[4].trim())
                .date(LocalDate.parse(fields[5], DATE_FORMATTER))
                .priceClose(new BigDecimal(fields[0]))
                .priceOpen(new BigDecimal(fields[3]))
                .priceHigh(new BigDecimal(fields[1]))
                .priceLow(new BigDecimal(fields[2]))
                .build();
    }
}
