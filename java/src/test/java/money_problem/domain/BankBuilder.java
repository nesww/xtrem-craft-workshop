package money_problem.domain;

import java.util.HashMap;

public class BankBuilder {
    private Currency currency;
    private HashMap<Currency , Double> exchangesRates;

    public static BankBuilder aBank() {
    }

    public BankBuilder withPivotCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BankBuilder withExchangeRate(Currency currency, double v) {
        this.exchangesRates.put(currency,v);
        return this;
    }

    public Bank build() {
        return Bank.withExchangeRate(currency, exchangesRates.keySet().toArray()[0]);
    }
}
