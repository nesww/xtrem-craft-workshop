package money_problem.domain;

import java.util.HashMap;

public class BankBuilder {
    private Currency currency;
    private HashMap<Currency , Double> exchangesRates;

    private BankBuilder() {
        exchangesRates = new HashMap();
    }

    public static BankBuilder aBank() {
        return new BankBuilder();
    }

    public BankBuilder withPivotCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BankBuilder withExchangeRate(Currency currency, double v)  throws  InvalidArgumentException{
        if (v <= 0) {
            throw new InvalidArgumentException("Exchange rate should not be negative.");
        }
        this.exchangesRates.put(currency,v);
        return this;
    }

    public Bank build() {
        return Bank.withExchangesRates(currency, exchangesRates);
    }
}
