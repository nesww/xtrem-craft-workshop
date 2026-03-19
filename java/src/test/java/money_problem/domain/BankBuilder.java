package money_problem.domain;

import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;

public class BankBuilder {
    private Currency currency;

    public static BankBuilder aBank() {
    }

    public BankBuilder withPivotCurrency(Currency currency) {
        this.currency = currency;
    }

    public BankBuilder withExchangeRate(Currency currency, double v) {

    }

    public Bank build() {
        return new Bank()
    }
}
