package money_problem.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Portfolio {

    Map<Currency, Double> money = new HashMap<>();

    public double Evaluate(Bank bank, Currency currency)
        throws MissingExchangeRateException {
        double sum = 0;
        for (Entry<Currency, Double> current : money.entrySet()) {
            sum += bank.convert(current.getValue(), current.getKey(), currency);
        }
        return sum;
    }

    public double Add(int amount, Currency currency) {
        money.merge(currency, (double) amount, Double::sum);
        return money.get(currency);
    }
}
