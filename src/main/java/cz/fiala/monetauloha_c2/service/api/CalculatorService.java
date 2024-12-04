package cz.fiala.monetauloha_c2.service.api;

import cz.fiala.monetauloha_c2.model.dto.CalculatorInputDto;
import cz.fiala.monetauloha_c2.model.dto.CalculatorOutputDto;

public interface CalculatorService {

    CalculatorOutputDto calculateSolution(CalculatorInputDto calculatorInput);

}
