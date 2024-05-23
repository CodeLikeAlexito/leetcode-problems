public class Account {
  private int balance = 0;

  public Account(int balance) {
    this.balance = balance;
  }

  public String deposit(int money) {
    balance += money;
    return String.format("Depositing $%d", money);
  }

  public String withdraw(int money) {
    if(balance < money) {
      return String.format("Withdrawing $%d (Insufficient Balance)", money);
    }
    balance -= money;
    return String.format("Withdrawing $%d", money);
  }

  public int getBalance() {
    return this.balance;
  }
}
