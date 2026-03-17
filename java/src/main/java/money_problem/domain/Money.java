package money_problem.domain;

public record Money (double amount, Currency currency) {
    public Money add(Money other) throws Exception{
        if (other.currency != this.currency){
            throw new Exception("Feur ! ");
        }
        return new Money(other.amount + this.amount, this.currency);
    }
    
    public Money times(int times) {
        return new Money(times* this.amount, this.currency);
    }
    
    public Money divide(int by){
        return new Money(this.amount / by, this.currency);
    }

    public boolean hasSameCurrency(Money other) {
        return this.currency() == other.currency();
    }
}
