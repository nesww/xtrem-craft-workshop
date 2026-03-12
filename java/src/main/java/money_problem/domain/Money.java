package money_problem.domain;

public record Money (double amount, Currency currency) {
    public Money add(double amount) {
        return new Money(amount + this.amount, this.currency);
    }
    public Money times(double amount) {
        return new Money(amount * this.amount, this.currency);
    }
    public Money divide(double amount) {
        return new Money(this.amount / amount, this.currency);
    }
}
