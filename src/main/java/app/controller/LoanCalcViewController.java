package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;

	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private Label lblTotalInterest;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		// Read input value
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dInterestRate = 0;
		String sInterestRate = InterestRate.getText();
		if (sInterestRate.contains("%")) {
			dInterestRate = Double.parseDouble(sInterestRate.substring(0,sInterestRate.indexOf("%"))) / 100.0;
		} else {
			dInterestRate = Double.parseDouble(sInterestRate);
		}
		double dNbrOfYears = Double.parseDouble(NbrOfYears.getText());
		double dAdditionalPayment = Double.parseDouble(AdditionalPayment.getText());
		LocalDate localDate = PaymentStartDate.getValue();
		
		// Print out input values on the console
		System.out.println("Loan Amount:        " + dLoanAmount);
		System.out.println("Interest Rate:      " + dInterestRate);
		System.out.println("Term of Loan/years: " + dNbrOfYears);
		System.out.println("Additional Payment: " + dAdditionalPayment);
		System.out.println("Payment Start Date: " + localDate);
		
		// Obtain the basic information for calculation
		
		double periodsPerYear = 12;
		double periodInterest = dInterestRate / periodsPerYear; 
		int numOfPeriods   = (int) (dNbrOfYears * periodsPerYear);
		double monthlyPayment = -(pmt(periodInterest, numOfPeriods, dLoanAmount));
		System.out.println("Monthly Payment:    " + monthlyPayment);
		double totalPayment   = monthlyPayment * numOfPeriods;
		double totalInterest  = totalPayment - dLoanAmount;
		lblTotalPayemnts.setText(String.format("%.2f", totalPayment));
		lblTotalInterest.setText(String.format("%.2f", totalInterest));
		
		// Initialize parameters for calculation
		int PaymentNum    = 1;
		DatePicker DueDate = null;
		double Payment    = monthlyPayment;
		double Interest   = ipmt(periodInterest, PaymentNum, numOfPeriods, -dLoanAmount);
		double Principle  = ppmt(periodInterest, PaymentNum, numOfPeriods, -dLoanAmount);
		double Balance    = dLoanAmount;
		
		// Calculation
		for (; Balance > 0; PaymentNum++) {
			DueDate.setValue(localDate.plusDays(30 * (PaymentNum - 1)));
			Interest  = ipmt(periodInterest, 1, numOfPeriods, -Balance);
			Principle = Payment - Interest + dAdditionalPayment;
			if ((Balance + Interest) < Payment) {
				Principle = Balance + dAdditionalPayment;
				Balance   = -dAdditionalPayment;
				break;
			} else {
				Balance   = Balance - Principle;
			}
		}
	}

	// Helper Functions on PMT, IPMT, PPMT
	static public double pmt(double r, int nper, double pv, double fv, int type) {
        return -r * (pv * Math.pow(1 + r, nper) + fv) / ((1 + r*type) * (Math.pow(1 + r, nper) - 1));
	}
	static public double pmt(double r, int nper, double pv, double fv) {
	    return pmt(r, nper, pv, fv, 0);
	}
	static public double pmt(double r, int nper, double pv) {
	    return pmt(r, nper, pv, 0);
	}
	static public double ipmt(double r, int per, int nper, double pv, double fv, int type) {
	    double ipmt = fv(r, per - 1, pmt(r, nper, pv, fv, type), pv, type) * r;
	    if (type==1) ipmt /= (1 + r);
	    return ipmt;
	}
	static public double ipmt(double r, int per, int nper, double pv, double fv) {
		return ipmt(r, per, nper, pv, fv, 0);
	}
	static public double ipmt(double r, int per, int nper, double pv) {
		return ipmt(r, per, nper, pv, 0);
	}
	static public double ppmt(double r, int per, int nper, double pv, double fv, int type) {
	    return pmt(r, nper, pv, fv, type) - ipmt(r, per, nper, pv, fv, type);
	}
	static public double ppmt(double r, int per, int nper, double pv, double fv) {
	    return pmt(r, nper, pv, fv) - ipmt(r, per, nper, pv, fv);
	}
	static public double ppmt(double r, int per, int nper, double pv) {
	    return pmt(r, nper, pv) - ipmt(r, per, nper, pv);
	}
	static public double fv(double r, int nper, double pmt, double pv, int type) {
        return -(pv * Math.pow(1 + r, nper) + pmt * (1+r*type) * (Math.pow(1 + r, nper) - 1) / r);
	}
	static public double fv(double r, int nper, double c, double pv) {
		return fv(r, nper, c, pv, 0);
	}
}
