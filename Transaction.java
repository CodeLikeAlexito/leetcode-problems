import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
  private final Account account;
  private final List<String> transactions;
  private final AtomicInteger transactionCount;

  public Transaction(Account account) {
    this.account = account;
    this.transactions = new CopyOnWriteArrayList<>();
    this.transactionCount = new AtomicInteger(0);
  }

  public synchronized void deposit(int money){
    if (transactionCount.incrementAndGet() > 10_000) {
      throw new RuntimeException("Transaction limit exceeded");
    }
    String transactionMessage = this.account.deposit(money);
    this.transactions.add(transactionMessage);
  }

  public synchronized  void withdraw(int money) {
    if (transactionCount.incrementAndGet() > 10_000) {
      throw new RuntimeException("Transaction limit exceeded");
    }
    String transactionMessage = this.account.withdraw(money);
    this.transactions.add(transactionMessage);
  }

  public List<String> getTransactions(){
    return this.transactions;
  }
}
