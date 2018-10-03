/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
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
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.dto.SupplierDTO;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class AddNewTransactionController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbSupplier;
    @FXML
    private JFXTextField txtSupplier;
    @FXML
    private JFXTextField txtGradeA;
    @FXML
    private JFXTextField txtGradeB;
    @FXML
    private JFXButton btnAdd;
    SupplierBO supplierBO;
    PurchaseBO purchaseBO;
    public AddNewTransactionController() {
        this.purchaseBO = (PurchaseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE);
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCmb();
    }    
    private void setCmb(){
        ArrayList<String> supplierids = new ArrayList<>();
        try {
            ArrayList<SupplierDTO> suppliers = supplierBO.getSuppliers();
            for (SupplierDTO supplier : suppliers) {
                supplierids.add(supplier.getSupplierid()+"");
            }
        } catch (Exception ex) {
            Logger.getLogger(AddNewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbSupplier.setItems(FXCollections.observableArrayList(supplierids));
    }

    @FXML
    private void cmbChanged(ActionEvent event) {
        try {
            SupplierDTO findSupplier = supplierBO.findSupplier(Integer.parseInt(cmbSupplier.getValue()));
            if(findSupplier == null || findSupplier.getName().equals(""))
                return;
            txtSupplier.setText(findSupplier.getName());
        } catch (Exception ex) {
            Logger.getLogger(AddNewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
        if(!txtGradeA.getText().matches("^\\d*(\\.?\\d{0,3})$") || !txtGradeB.getText().matches("^\\d*(\\.?\\d{0,3})$")){
            new Alert(Alert.AlertType.ERROR, "Please enter a valid quantity", ButtonType.OK).show();
            return;
        }
        try {
            boolean result = purchaseBO.addPurchase(new PurchaseDTO(-1, new Date(), Integer.parseInt(cmbSupplier.getValue()), txtSupplier.getText(), new BigDecimal(txtGradeA.getText()), new BigDecimal(txtGradeB.getText())));
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Transaction added successfully!", ButtonType.OK).show();
                clearData();
            }else{
                new Alert(Alert.AlertType.ERROR, "Transaction not added.. Please try again!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(AddNewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clearData(){
        txtSupplier.setText("");
        txtGradeA.setText("");
        txtGradeB.setText("");
        cmbSupplier.getSelectionModel().clearSelection();
    }
    
}
