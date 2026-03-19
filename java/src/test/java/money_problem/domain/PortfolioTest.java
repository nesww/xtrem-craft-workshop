package money_problem.domain;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PortfolioTest {

    @Test
    void evaluate_empty_portfolio() throws MissingExchangeRateException, InvalidArgumentException {
        //Given
        Bank bank = BankBuilder.aBank()
                .withPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        int expectedResult = 0;
        Portfolio portfolio = new Portfolio();

        //When
        double evalutionPortfolio = portfolio.Evaluate(bank, EUR);

        //Then
        assertThat(evalutionPortfolio).isEqualTo(expectedResult);
    }

    @Test
    void evaluate_portfolio_amount_in_different_currencies()
            throws MissingExchangeRateException, InvalidArgumentException {
        //Given
        Bank bank = BankBuilder.aBank()
                .withPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();

        double expectedResult1 = 18.33;
        int expectedResult2 = 22;
        Portfolio portfolio = new Portfolio();
        Money m_eur = new Money(10, EUR);
        Money m_usd = new Money(10, USD);
        
        assertThat(portfolio.Add(m_eur)).isEqualTo(new Money(10, EUR));
        assertThat(portfolio.Add(m_usd)).isEqualTo(new Money(10, USD));

        //When
        double evalutionPortfolio1 = portfolio.Evaluate(bank, EUR);
        double evalutionPortfolio2 = portfolio.Evaluate(bank, USD);

        //Then
        assertThat(evalutionPortfolio1).isEqualTo(expectedResult1);
        assertThat(evalutionPortfolio2).isEqualTo(expectedResult2);
    }

    @Test
    void evaluate_portfolio_with_unknown_exchange_rates() throws InvalidArgumentException {
        //Given
        Bank bank = BankBuilder.aBank()
                .withPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        Portfolio portfolio = new Portfolio();
        portfolio.Add(new Money(10, KRW));

        //When
        assertThrows(MissingExchangeRateException.class, () -> portfolio.Evaluate(bank, EUR));
    }
}
