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

    public BankBuilder withExchangeRate(Currency currency, double v) {
        this.exchangesRates.put(currency,v);
        return this;
    }

    public Bank build() {
        Bank bank = Bank.withExchangeRate(currency, (Currency) exchangesRates.keySet().toArray()[0], (Double) exchangesRates.values().toArray()[0]);
        for (int i = 1; i < exchangesRates.size(); i++) {
            bank.addExchangeRate(currency, (Currency) exchangesRates.keySet().toArray()[i], (Double) exchangesRates.values().toArray()[i]);
        }
        return bank;
    }
}
