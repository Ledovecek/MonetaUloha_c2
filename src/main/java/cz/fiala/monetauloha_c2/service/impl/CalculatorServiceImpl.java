package cz.fiala.monetauloha_c2.service.impl;

import cz.fiala.monetauloha_c2.model.dto.CalculatorInputDto;
import cz.fiala.monetauloha_c2.model.dto.CalculatorOutputDto;
import cz.fiala.monetauloha_c2.service.api.CalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    /**
     * Funkce, která udělá transformaci čísla podle zadání.
     */
    @Override
    public CalculatorOutputDto calculateSolution(CalculatorInputDto calculatorInput) {
        StringBuilder stringBuffer = new StringBuilder(calculatorInput.getInput());

        // Kontrola jestli se jedná o celé číslo
        for (int i = stringBuffer.length() - 1; i >= 0; i--) {
            char c = stringBuffer.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Input must be a number!");
            }
        }

        // Zde posouváme veškerá čísla 3 a menší o index doprava
        for (int i = stringBuffer.length() - 1; i >= 0; i--) {
            char c = stringBuffer.charAt(i);
            if (c <= '3') {
                int rightSlot = i + 1;
                if (rightSlot < stringBuffer.length()) {
                    char rightSlotChar = stringBuffer.charAt(rightSlot);
                    stringBuffer.setCharAt(rightSlot, c);
                    stringBuffer.setCharAt(i, rightSlotChar);
                }
            }
        }

        for (int i = 0; i < stringBuffer.length(); i++) {
            char c = stringBuffer.charAt(i);
            if (c == '8' || c == '9') {
                // Čísla 8 a 9 vynásobíme dvěmi
                stringBuffer.setCharAt(i, '1');
                stringBuffer.insert(i + 1, c == '8' ? '6' : '8');
                i++;
            } else if (c == '7') {
                // Odebíráme z čísla veškeré číslice 7
                stringBuffer.deleteCharAt(i);
                i--;
            }
        }

        // Pokud po transformaci nezbyla žádná čísla, tak vrátíme prázdný výsledek
        if (stringBuffer.isEmpty()) {
            return new CalculatorOutputDto("");
        }

        // Zde počítáme počet sudých čísel
        int evenNumbers = 0;
        for (int i = 0; i < stringBuffer.length(); i++) {
            char c = stringBuffer.charAt(i);
            if (c % 2 == 0) {
                evenNumbers++;
            }
        }

        // Zde vydělíme počtem sudých čísel, pokud je počet sudých čísel větší jak 0
        BigInteger bigInteger = new BigInteger(stringBuffer.toString());
        if (evenNumbers != 0) {
            bigInteger = bigInteger.divide(BigInteger.valueOf(evenNumbers));
        }

        return new CalculatorOutputDto(bigInteger.toString());
    }

}
