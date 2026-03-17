package money_problem.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Portfolio {

    List<Money> money = new ArrayList<>();


    public double Evaluate(Bank bank, Currency currency)
        throws MissingExchangeRateException {
        double sum = 0;
        for (Money current : money) {
            sum += bank.convert(current, currency);
        }
        return sum;
    }

    public Money Add(Money other) {
        for(int i = 0; i < money.size(); ++i) {
            if (money.get(i).hasSameCurrency(other)) {
                try {
                    money.set(i, money.get(i).add(other));
                    return money.get(i);
                } catch(Exception e) {
                    return null;
                }
                
            }
        }
        money.add(other);
        return other;
    }
}
