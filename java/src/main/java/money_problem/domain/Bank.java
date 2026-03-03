package money_problem.domain;

import java.util.HashMap;
import java.util.Map;

public final class Bank {
    private final Map<String, Double> exchangeRates;

    private Bank(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public static Bank withExchangeRate(Currency currency1, Currency currency2, double rate) {
        var bank = new Bank(new HashMap<>());
        bank.addExchangeRate(currency1, currency2, rate);

        return bank;
    }

    public void addExchangeRate(Currency currency1, Currency currency2, double rate) {
        exchangeRates.put(currency1 + "->" + currency2, rate);
    }

    public double convert(double amount, Currency from, Currency to) throws MissingExchangeRateException {
        if (!convertable(from, to)) {
            throw new MissingExchangeRateException(from,to);
        }
        return from == to
                ? amount
                : amount * exchangeRates.get(from + "->" + to);
    }

    private boolean convertable(Currency from, Currency to){
        return ((from == to || hasExchangeRates(from, to)));
    }

    private boolean hasExchangeRates(Currency from, Currency to) {
        return exchangeRates.containsKey(from + "->" + to);
    }

}