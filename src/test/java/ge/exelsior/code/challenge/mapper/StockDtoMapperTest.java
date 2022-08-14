package ge.exelsior.code.challenge.mapper;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockDtoMapperTest {

    @Test
    void createStockDto() {
        String[] inputData = new String[6];
        inputData[0] = "774.39";
        inputData[1] = "774.83";
        inputData[2] = "744.1";
        inputData[3] = "753.99";
        inputData[4] = "TSLA";
        inputData[5] = "9/24/2021";
        var dto = StockDtoMapper.createStockDto(inputData);

        assertEquals(new BigDecimal("774.39"), dto.getPriceClose());
        assertEquals(new BigDecimal("774.83"), dto.getPriceHigh());
        assertEquals(new BigDecimal("744.1"), dto.getPriceLow());
        assertEquals(new BigDecimal("753.99"), dto.getPriceOpen());
        assertEquals("TSLA", dto.getTickerName());
        assertEquals(LocalDate.parse("2021-09-24"), dto.getDate());
    }
}