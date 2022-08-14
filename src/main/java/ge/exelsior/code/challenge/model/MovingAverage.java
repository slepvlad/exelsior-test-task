package ge.exelsior.code.challenge.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovingAverage(LocalDate date, BigDecimal average) {

}
