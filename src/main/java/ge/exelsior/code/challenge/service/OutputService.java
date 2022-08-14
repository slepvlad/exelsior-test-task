package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.Stock;
import ge.exelsior.code.challenge.model.dto.InitialData;

import java.util.List;

public interface OutputService {

    void printStatistic(List<Stock> stockDtoList, InitialData initialData);
}
