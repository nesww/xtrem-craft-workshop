package money_problem.domain;

import java.util.HashMap;
import java.util.Map;

public final class Bank {
    private Map<Currency, Double> exchangeRates;

    private Currency pivot;

    private Bank(Currency pivot, Map<Currency, Double> exchangeRates) {
        this.pivot= pivot;
        this.exchangeRates = exchangeRates;
    }

    public static Bank withExchangeRate(Currency pivot, Currency currency, double rate) {
        var bank = new Bank(pivot,new HashMap<>());

        bank.addExchangeRate(currency, rate);

        return bank;
    }

    public static Bank withExchangesRates(Currency pivot, Map<Currency, Double> exchangesRates) {
        var bank = new Bank(pivot,new HashMap<>());
        bank.exchangeRates = exchangesRates;
        return bank;
    }

    public void addExchangeRate(Currency currency, double rate) {
        exchangeRates.put(currency, rate);
    }

    public double convert(Money from, Currency to) throws MissingExchangeRateException {
        if (!convertable(from.currency(), to)) {
            throw new MissingExchangeRateException(from.currency(),to);
        }
        return from.currency() == to
                ? from.amount()
                : from.amount() * exchangeRates.get(from.currency() + "->" + to);
    }

    private boolean convertable(Currency from, Currency to){
        return ((from == to || hasExchangeRates(from, to)));
    }

    private boolean hasExchangeRates(Currency from, Currency to) {
        return exchangeRates.containsKey(from + "->" + to);
    }

}