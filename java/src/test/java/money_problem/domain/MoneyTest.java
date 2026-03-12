package money_problem.domain;

import org.junit.jupiter.api.Test;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void shouldAddInUsd() {
        Money m  = new Money(3, USD);
        assertThat(m.add(3)).isEqualTo(new Money(6, USD));
    }

    @Test
    void shouldMultiplyInEuros() {
        Money m  = new Money(3, EUR);
        assertThat(m.times(3)).isEqualTo(new Money(9, EUR));
    }

    @Test
    void shouldDivideInKoreanWons() {
        Money m  = new Money(3, KRW);
        assertThat(m.divide(3)).isEqualTo(new Money(1, KRW));
    }
}