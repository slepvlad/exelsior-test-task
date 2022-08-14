package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.dto.StockDto;

import java.util.List;

public interface CvsFileService {

     List<StockDto> readFile(String path);
}
