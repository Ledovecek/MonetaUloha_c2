package cz.fiala.monetauloha_c2.rest;

import cz.fiala.monetauloha_c2.model.dto.CalculatorInputDto;
import cz.fiala.monetauloha_c2.model.dto.CalculatorOutputDto;
import cz.fiala.monetauloha_c2.service.impl.CalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorServiceImpl calculatorService;

    @Autowired
    public CalculatorController(CalculatorServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping
    public ResponseEntity<CalculatorOutputDto> solveAssignment(@RequestBody CalculatorInputDto calculatorInputDto) {
        CalculatorOutputDto calculatorOutputDto;
        try {
            calculatorOutputDto = calculatorService.calculateSolution(calculatorInputDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CalculatorOutputDto(e.getMessage()));
        }
        return ResponseEntity.ok().body(calculatorOutputDto);
    }

}
