/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.CreditBO;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.dto.CreditDTO;
import lk.beempz.tf.view.tblmodel.CreditTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class SupplierAccountsController implements Initializable {

    @FXML
    private JFXDatePicker dateFrom;
    @FXML
    private JFXDatePicker dateTo;
    @FXML
    private JFXButton btnShow;
    @FXML
    private JFXComboBox<String> cmbSortBy;
    @FXML
    private Label lblTotal;
    @FXML
    private TableView<CreditTM> tblTransaction;
    ArrayList<CreditTM> debits = new ArrayList<>();
    @FXML
    private AnchorPane root;
    CreditBO creditBO;

    public SupplierAccountsController() {
        this.creditBO = (CreditBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CREDIT);
       
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblTransaction.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTransaction.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplierno"));
        tblTransaction.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblTransaction.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblTransaction.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("amount"));
        loadTable();
    }

    @FXML
    private void btn_Show_OnClick(ActionEvent event) {
        if(dateFrom.getValue() == null || dateTo.getValue() == null)
            return;
        ArrayList<CreditTM> creditTMs = new ArrayList<>();
        Date from = Date.from(dateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(dateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            ArrayList<CreditDTO> credits = creditBO.getAllCredits(from, to);
            for (CreditDTO credit : credits) {
                creditTMs.add(new CreditTM(credit.getCreditid(), credit.getDate(), credit.getSupplierid(), credit.getSuppliername(), credit.getCredit_typename(), credit.getAmount()));
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccountsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable(creditTMs);
    }

    @FXML
    private void tblClicked(MouseEvent event) {
    }

    private void loadTable() {
        ArrayList<CreditTM> creditTMs = new ArrayList<>();
        try {
            ArrayList<CreditDTO> allCredits = creditBO.getAllCredits(null, null);
            for (CreditDTO allCredit : allCredits) {
                creditTMs.add(new CreditTM(allCredit.getCreditid(), allCredit.getDate(), allCredit.getSupplierid(), allCredit.getSuppliername(), allCredit.getCredit_typename(), allCredit.getAmount()));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SupplierAccountsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable(creditTMs);
    }
    public void setTable(ArrayList<CreditTM> creditTMs){
        tblTransaction.setItems(FXCollections.observableArrayList(creditTMs));
    }

    @FXML
    private void btnClickhere_OnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/TodayTransactions.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SupplierAccountsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
