/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
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
import lk.beempz.tf.business.custom.BranchBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.business.custom.Supplier_BankBO;
import lk.beempz.tf.business.custom.impl.Supplier_BankBOImpl;
import lk.beempz.tf.dto.BranchDTO;
import lk.beempz.tf.dto.SupplierDTO;
import lk.beempz.tf.dto.Supplier_BankDTO;
import lk.beempz.tf.view.tblmodel.SupplierTM;

/**
 * FXML Controller class
 *
 * @author beempz
 */
public class AddCustomerController implements Initializable {

    @FXML
    private JFXTextField txtSupName;
    @FXML
    private JFXTextField txtContactNo;
    @FXML
    private JFXTextField txtAdress;
    @FXML
    private JFXComboBox<String> cmbRoute;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXComboBox<String> cmbBank;
    @FXML
    private JFXTextField txtAccNo;
    /**
     * Initializes the controller class.
     */
    private SupplierBO supplierBO;
    private Supplier_BankBO supplier_BankBOImpl;
    private boolean update;
    private BranchBO branchBO;
    public AddCustomerController() {
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        this.supplier_BankBOImpl = (Supplier_BankBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_BANK);
        this.branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<BranchDTO> branches = null;
        ArrayList<String> branchIds = new ArrayList<>();
        try {
            branches = branchBO.getBranches();
        } catch (Exception ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (BranchDTO branch : branches) {
            branchIds.add(branch.getBranchid()+"");
        }
        cmbBank.setItems(FXCollections.observableArrayList(branchIds));
        
        if(CustomerFormController.SUPPLIER != null){
            SupplierTM supplierTM = CustomerFormController.SUPPLIER;
            this.txtSupName.setText(supplierTM.getName());
            this.txtAdress.setText(supplierTM.getAddress());
            this.txtContactNo.setText(supplierTM.getContact());
            this.cmbRoute.getSelectionModel().select(supplierTM.getRoute());
            update = true;
        }
        
    }    

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        if(update){
            SupplierDTO supplier = new SupplierDTO(CustomerFormController.SUPPLIER.getSupplierid(), this.txtSupName.getText(),-1, this.cmbRoute.getSelectionModel().getSelectedItem(), this.txtContactNo.getText(), txtAdress.getText());
            try {
                boolean result = supplierBO.updateSupplier(supplier);
                if(result){
                    new Alert(Alert.AlertType.INFORMATION, "Supplier updated!", ButtonType.OK).show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Updating supplier failed!", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            SupplierDTO supplier = new SupplierDTO(-1, txtSupName.getText(), -1, cmbRoute.getSelectionModel().getSelectedItem(), txtContactNo.getText(), txtAdress.getText());
            try {
                int result = supplierBO.addAndReturnGenerated(supplier);
                if(result>0){
                    supplier_BankBOImpl.addSuplierBank(new Supplier_BankDTO(Integer.parseInt(cmbBank.getSelectionModel().getSelectedItem()), result, txtAccNo.getText()));
                    new Alert(Alert.AlertType.INFORMATION, "Supplier added!", ButtonType.OK).show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Adding supplier failed!", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
