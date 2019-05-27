package app;

import javafx.beans.property.SimpleStringProperty;

public class LoanPeriod {
  private final SimpleStringProperty paymentNum;
  private final SimpleStringProperty dueDate;
  private final SimpleStringProperty payment;
  private final SimpleStringProperty additionalPayment;
  private final SimpleStringProperty interest;
  private final SimpleStringProperty principle;
  private final SimpleStringProperty balance;

  public LoanPeriod(String paymentNum, String dueDate, String payment, String additionalPayment, String interest, String principle, String balance) {
      this.paymentNum = new SimpleStringProperty(paymentNum);
      this.dueDate = new SimpleStringProperty(dueDate);
      this.payment = new SimpleStringProperty(payment);
      this.additionalPayment = new SimpleStringProperty(additionalPayment);
      this.interest = new SimpleStringProperty(interest);
      this.principle = new SimpleStringProperty(principle);
      this.balance = new SimpleStringProperty(balance);
  }
}
