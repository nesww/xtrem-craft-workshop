package money_problem.domain;

import org.junit.jupiter.api.Test;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BankTest {

    @Test
    void convert_from_currency_to_other_currency_returns_double_convertions() throws MissingExchangeRateException {
        
        //Given
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        int expectedAmount = 12;
        
        //When
        double amount = bank.convert(10, EUR, USD);
        
        //Then
        assertThat(amount).isEqualTo(expectedAmount);
    }

    @Test
    void convert_currency_to_same_currency_returns_same_value() throws MissingExchangeRateException {
        //Given
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        int expectedAmount = 10;

        //When 
        double amount = bank.convert(10, EUR, EUR);

        //Then
        assertThat(amount)
                .isEqualTo(expectedAmount);
    }

    @Test
    void convert_currency_to_currency_with_unknown_exchange_rate_throws_exception_on_missing_exchange_rate() {
        assertThatThrownBy(() -> {
            Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
            bank.convert(10, EUR, KRW);
        })
                .isInstanceOf(MissingExchangeRateException.class)
                .hasMessage("EUR->KRW");
    }

    @Test
    void convert_FromTo_with_different_exchange_rates_returns_different_amounts() throws MissingExchangeRateException {
        //Given
        Bank bank = Bank.withExchangeRate(EUR, USD, 1.2);
        int expectedAmount = 12;
        Bank bank2 = Bank.withExchangeRate(EUR, USD, 1.3);
        int expectedAmount2 = 13;

        //when
        double amount = bank.convert(10, EUR, USD);
        double amount2 = bank2.convert(10, EUR, USD);

        //Then
        assertThat(amount)
                .isEqualTo(expectedAmount);
        assertThat(amount2)
                .isEqualTo(expectedAmount2);
    }
}