package money_problem.domain;

import java.util.HashMap;
import java.util.Map;

public final class Bank {
    private Map<Currency, Double> exchangeRates;

    private Currency pivot;

    private Bank(Currency pivot, Map<Currency, Double> exchangeRates) {
        this.pivot = pivot;
        this.exchangeRates = exchangeRates;
        this.exchangeRates.put(pivot,1.);
    }

    public static Bank withExchangeRate(Currency pivot, Currency currency, double rate) {
        var bank = new Bank(pivot,new HashMap<>());

        bank.addExchangeRate(currency, rate);

        return bank;
    }

    public static Bank withExchangesRates(Currency pivot, Map<Currency, Double> exchangesRates) {
        return new Bank(pivot,exchangesRates);
    }

    public void addExchangeRate(Currency currency, double rate) {
        exchangeRates.put(currency, rate);
    }

    public double convert(Money from, Currency to) throws MissingExchangeRateException {
        if (!convertable(from.currency(), to)) {
            throw new MissingExchangeRateException(from.currency(),to);
        }
        double valueInPivot = from.amount() / exchangeRates.get(from.currency());
        return Math.round(valueInPivot * exchangeRates.get(to)*100.0)/100.0;
    }

    private boolean convertable(Currency from, Currency to){
        return ((from == to || (hasExchangeRates(from) && hasExchangeRates(to))));
    }

    private boolean hasExchangeRates(Currency to) {
        return exchangeRates.containsKey(to);
    }

}