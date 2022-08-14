package ge.exelsior.code.challenge;

import ge.exelsior.code.challenge.model.dto.InitialData;
import ge.exelsior.code.challenge.service.*;

public class App {

    public static void main(String[] arg) {
        //Number of elements (N in task)
        int elements = Integer.parseInt(arg[1]);
        //Number of days (M in task)
        int days = Integer.parseInt(arg[2]);
        String filePath = arg[0];

        var initialData = new InitialData(filePath, days, elements);

        ValidationService validationService = new ValidationServiceImpl();
        validationService.validate(initialData);

        CvsFileService fileService = new CvsFileServiceImpl();
        StockService stockService = new StockServiceImpl();
        OutputService outputService = new ConsoleOutputService(stockService);

        var stockDtoList = fileService.readFile(initialData.path());
        var stocks = stockService.createStocks(stockDtoList);
        outputService.printStatistic(stocks, initialData);
    }
}
