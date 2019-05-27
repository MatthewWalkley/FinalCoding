package app.controller;

import app.LoanPeriod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewHandler {
  @FXML
  private TableView loanTable;
  
  @SuppressWarnings("restriction")
  public ObservableList<LoanPeriod> loanPeriod = 
      FXCollections.observableArrayList(
          new LoanPeriod("Jacob", "Smith", "jacob.smith@example.com", "1", "1", "1", "1"),
          new LoanPeriod("Isabella", "Johnson", "isabella.johnson@example.com", "1", "1", "1", "1"));
  
  public TableViewHandler(TableView lt){
    this.loanTable = lt;
  };
  
  public void initTableViewHandler(){
    loanTable.setEditable(true);
    TableColumn temp = (TableColumn) loanTable.getColumns().get(0);
    temp.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("paymentNum"));
   
    TableColumn temp1 = (TableColumn) loanTable.getColumns().get(1);
    temp1.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("dueDate"));
    
    TableColumn temp2 = (TableColumn) loanTable.getColumns().get(2);
    temp2.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("payment"));
    
    TableColumn temp3 = (TableColumn) loanTable.getColumns().get(3);
    temp3.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("additionalPayment"));
    
    TableColumn temp4 = (TableColumn) loanTable.getColumns().get(4);
    temp4.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("interest"));
    
    TableColumn temp5 = (TableColumn) loanTable.getColumns().get(5);
    temp5.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("principle"));
    
    TableColumn temp6 = (TableColumn) loanTable.getColumns().get(6);
    temp6.setCellValueFactory(new PropertyValueFactory<LoanPeriod, String>("balance"));
    
    loanTable.setItems(loanPeriod);
    loanTable.getColumns().addAll(temp, temp1, temp2, temp3, temp4, temp5, temp6);
  }
}
