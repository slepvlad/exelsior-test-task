package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.mapper.StockDtoMapper;
import ge.exelsior.code.challenge.model.dto.StockDto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CvsFileServiceImpl implements CvsFileService {


    @Override
    public List<StockDto> readFile(String path){
        try {
            return new BufferedReader(new FileReader(path))
                    .lines()
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(StockDtoMapper::createStockDto)
                    .toList();
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file path");
            throw new IllegalArgumentException("Wrong file path");
        }
    }
}
