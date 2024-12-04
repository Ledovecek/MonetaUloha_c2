package cz.fiala.monetauloha_c2.model.dto;

public class CalculatorOutputDto {

    private String result;

    public CalculatorOutputDto() {
    }

    public CalculatorOutputDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
