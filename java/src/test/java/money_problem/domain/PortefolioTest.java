package money_problem.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PortefolioTest {

    @Test 
    void evaluate_empty_portfolio() {
        //Given 
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        int expectedResult = 0;
        Portfolio portfolio = new Portfolio();
        
        //When 
        double evalutionPortfolio = portfolio.Evaluate(bank,EUR);

        //Then
        assertThat(evalutionPortfolio).isEqualTo(expectedResult);
    }   
}
