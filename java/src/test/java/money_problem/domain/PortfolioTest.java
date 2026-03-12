package money_problem.domain;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PortfolioTest {

    @Test
    void evaluate_empty_portfolio() throws MissingExchangeRateException {
        //Given
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        int expectedResult = 0;
        Portfolio portfolio = new Portfolio();

        //When
        double evalutionPortfolio = portfolio.Evaluate(bank, EUR);

        //Then
        assertThat(evalutionPortfolio).isEqualTo(expectedResult);
    }

    @Test
    void evaluate_portfolio_amount_in_different_currencies()
        throws MissingExchangeRateException {
        //Given
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        bank.addExchangeRate(USD, EUR, 0.8);
        int expectedResult1 = 18;
        int expectedResult2 = 22;
        Portfolio portfolio = new Portfolio();

        assertThat(portfolio.Add(10, EUR)).isEqualTo(10);
        assertThat(portfolio.Add(10, USD)).isEqualTo(10);

        //When
        double evalutionPortfolio1 = portfolio.Evaluate(bank, EUR);
        double evalutionPortfolio2 = portfolio.Evaluate(bank, USD);

        //Then
        assertThat(evalutionPortfolio1).isEqualTo(expectedResult1);
        assertThat(evalutionPortfolio2).isEqualTo(expectedResult2);
    }

    @Test
    void evaluate_portfolio_with_unknown_exchange_rates() {
        //Given
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        Portfolio portfolio = new Portfolio();
        portfolio.Add(10, KRW);

        //When
        assertThrows(MissingExchangeRateException.class, () -> portfolio.Evaluate(bank, EUR));
    }
}
