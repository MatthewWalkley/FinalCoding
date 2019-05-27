package app;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("restriction")
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


  public String toString() {
	  String a = "[ "+paymentNum+", "+dueDate+", "+payment+", "+additionalPayment+", "+interest+", "+principle+", "+balance+" ]";
	  return a;
  }
  
  public String getPaymentNum() {
	  return paymentNum.get();
  }
  public void setPaymentNum(String paymentNum) {
	  this.paymentNum.set(paymentNum);
  }
  
  public String getDueDate() {
	  return dueDate.get();
  }
  public void setDueDate(String dueDate) {
	  this.dueDate.set(dueDate);
  }
  
  public String getPayment() {
	  return payment.get();
  }
  public void setPayment(String payment) {
	  this.payment.set(payment);
  }
  
  public String getAdditionalPayment() {
	  return additionalPayment.get();
  }
  public void setAdditionalPayment(String additionalPayment) {
	  this.additionalPayment.set(additionalPayment);
  }
  
  public String getInterest() {
	  return interest.get();
  }
  public void setLoanInterest(String interest) {
	  this.interest.set(interest);
  }
  
  public String getPrinciple() {
	  return principle.get();
  }
  public void setPrinciple(String principle) {
	  this.principle.set(principle);
  }
  
  public String getBalance() {
	  return balance.get();
  }
  public void setBalance(String balance) {
	  this.balance.set(balance);
  }
}