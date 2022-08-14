package ge.exelsior.code.challenge.service;

import ge.exelsior.code.challenge.model.dto.InitialData;

import java.util.Objects;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validate(InitialData initialData) {
        validateDays(initialData.days());
        validateElements(initialData.elements());
        validatePath(initialData.path());
    }

    private void validateDays(int days) {
        if(days < 0) {
            System.out.println("Number of days should be positive");
            throw new IllegalArgumentException("Number of days should be positive");
        }
    }

    private void validateElements(int elements){
        if(elements < 1) {
            System.out.println("Number of elements should be more than zero");
            throw new IllegalArgumentException("Number of elements should be more than zero");
        }
    }

    private void validatePath(String path){
        if(Objects.isNull(path) || path.isBlank()) {
            System.out.println("Path should not be blank or null");
            throw new IllegalArgumentException("Path should not be blank or null");
        }
    }
}
