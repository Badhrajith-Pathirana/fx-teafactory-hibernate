/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.CreditBO;
import lk.beempz.tf.business.custom.CreditTypeBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dto.CreditDTO;
import lk.beempz.tf.dto.CreditTypeDTO;
import lk.beempz.tf.dto.SupplierDTO;
import lk.beempz.tf.view.tblmodel.CreditTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class TodayTransactionsController implements Initializable {

    
    @FXML
    private JFXComboBox<String> cmbSupplier;
    @FXML
    private JFXComboBox<String> cmbDescription;

    CreditBO creditBO;
    CreditTypeBO creditTypeBO;
    SupplierBO supplierBO;
    @FXML
    private JFXTextField Amount;
    @FXML
    private TableView<CreditTM> tblCredit;
    public TodayTransactionsController() {
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        this.creditTypeBO = (CreditTypeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CREDIT_TYPE);
        this.creditBO = (CreditBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CREDIT);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCredit.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblCredit.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplierno"));
        tblCredit.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCredit.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblCredit.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("amount"));
        setCmbs();
        setTable();
    }    
    private void setTable(){
        ArrayList<CreditTM> creditTMs = new ArrayList<>();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //c.add(Calendar.DATE, 1);
        Date date2 = c.getTime();
        c.add(Calendar.DATE, -1);
        date = c.getTime();
        try {
            ArrayList<CreditDTO> creditDTOs = creditBO.getAllCredits(date,date2);
            
            for (CreditDTO creditDTO : creditDTOs) {
                creditTMs.add(new CreditTM(creditDTO.getCreditid(), creditDTO.getDate(), creditDTO.getSupplierid(), creditDTO.getSuppliername(), creditDTO.getCredit_typename(), creditDTO.getAmount()));
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TodayTransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblCredit.setItems(FXCollections.observableArrayList(creditTMs));
    }

    private void setCmbs(){
        ArrayList<String> descs = new ArrayList<>();
        try {
            ArrayList<CreditTypeDTO> credits = creditTypeBO.getCredits();
            for (CreditTypeDTO credit : credits) {
                descs.add(credit.getCreditType());
            }
        } catch (Exception ex) {
            Logger.getLogger(TodayTransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbDescription.setItems(FXCollections.observableArrayList(descs));
        ArrayList<String> suppliers = new ArrayList<>();
        try {
            ArrayList<SupplierDTO> suppliersDTOs = supplierBO.getSuppliers();
            for (SupplierDTO suppliersDTO : suppliersDTOs) {
                suppliers.add(suppliersDTO.getSupplierid()+"");
            }
        } catch (Exception ex) {
            Logger.getLogger(TodayTransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbSupplier.setItems(FXCollections.observableArrayList(suppliers));
    }
    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
        if (cmbSupplier.getValue() == null || cmbDescription.getValue() == null || !Amount.getText().matches("^\\d*\\.?\\d{0,2}") ) {
            new Alert(Alert.AlertType.INFORMATION, "Please enter valid inputs", ButtonType.OK).show();
            return;
        }
        try {
            boolean result = creditBO.insertCredit(new CreditDTO(-12, Integer.parseInt(cmbSupplier.getValue()), null, -1, cmbDescription.getValue(), new Date(), new BigDecimal(Amount.getText())));
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Transaction added successfully!", ButtonType.OK).show();
                clearTypedData();
            }else{
                new Alert(Alert.AlertType.ERROR, "Transaction adding failed.", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(TodayTransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable();
    }
    private void clearTypedData(){
        Amount.setText("");
        cmbSupplier.getSelectionModel().clearSelection();
        cmbDescription.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnRemove_OnAction(ActionEvent event) {
        if(tblCredit.getSelectionModel().getSelectedIndex() == -1)
            return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete this transaction?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO)
            return;
        CreditTM selectedItem = tblCredit.getSelectionModel().getSelectedItem();
        try {
            boolean deleteCredit = creditBO.deleteCredit(new CreditDTO(selectedItem.getCreditid(), selectedItem.getSupplierno(), selectedItem.getName(), -1, selectedItem.getDescription(), selectedItem.getDate(), selectedItem.getAmount()));
            if(deleteCredit){
                new Alert(Alert.AlertType.INFORMATION, "Deleted successfully!", ButtonType.OK).show();
                clearTypedData();
            }else{
                new Alert(Alert.AlertType.ERROR, "credit deletion failed!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(TodayTransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable();
    }
    
}
