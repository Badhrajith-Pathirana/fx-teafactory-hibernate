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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.BankBO;
import lk.beempz.tf.business.custom.BranchBO;
import lk.beempz.tf.dto.BankDTO;
import lk.beempz.tf.dto.BranchDTO;
import lk.beempz.tf.view.tblmodel.BankTM;
import lk.beempz.tf.view.tblmodel.BranchTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class BanksAndBranchesController implements Initializable {

    @FXML
    private JFXTextField txtBank;
    @FXML
    private JFXButton btnBankAdd;
    @FXML
    private JFXButton btnBankRemove;
    @FXML
    private JFXButton btnBankEdit;
    @FXML
    private TableView<BankTM> tblBanks;
    @FXML
    private JFXTextField txtBranch;
    @FXML
    private JFXComboBox<String> cmbBank;
    @FXML
    private TableView<BranchTM> tblBranch;
    @FXML
    private JFXButton txtBRanchRemove;
    @FXML
    private JFXButton txtBranchEdit;
    @FXML
    private JFXButton txtBranchAdd;
    /**
     * Initializes the controller class.
     */
    BankBO bankBO;
    BranchBO branchBO;

    public BanksAndBranchesController() {
        this.branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
        this.bankBO = (BankBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BANK);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblBanks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bankid"));
        tblBanks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bankName"));
        tblBranch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("branchid"));
        tblBranch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("branchName"));
        tblBranch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bankName"));
        loadBanks();
        loadBranch();
    }  
    private void loadBanks(){
        ArrayList<BankTM> banks = new ArrayList<>();
        ArrayList<String> bankNames = new ArrayList<>();
        try {
            ArrayList<BankDTO> allBanks = bankBO.getAllBanks();
            for (BankDTO allBank : allBanks) {
                banks.add(new BankTM(allBank.getBankid(), allBank.getBankName()));
                bankNames.add(allBank.getBankName());
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblBanks.setItems(FXCollections.observableArrayList(banks));
        cmbBank.setItems(FXCollections.observableArrayList(bankNames));
    }

    @FXML
    private void bank_Add(ActionEvent event) {
        try {
            BankDTO saveBank = bankBO.saveBank(new BankDTO(-1, txtBank.getText()));
            if(saveBank == null){
                new Alert(Alert.AlertType.ERROR, "Inserting failed", ButtonType.OK).show();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Bank added successfully!",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadBanks();
    }

    @FXML
    private void bank_Remove(ActionEvent event) {
        if(tblBanks.getSelectionModel().getSelectedIndex() == -1){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete the bank?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO)
            return;
        BankTM bank = tblBanks.getSelectionModel().getSelectedItem();
        try {
            boolean result = bankBO.deleteBank(bank.getBankid());
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Bank deleted successfully!", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadBanks();
    }

    @FXML
    private void bank_Edit(ActionEvent event) {
        if(tblBanks.getSelectionModel().getSelectedIndex() == -1){
            return;
        }
        if (txtBank.getText().equals("")) {
            return;
        }
        BankTM bank = tblBanks.getSelectionModel().getSelectedItem();
        try {
            boolean result = bankBO.updateBank(new BankDTO(bank.getBankid(), txtBank.getText()));
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Bank name updated successfully", ButtonType.OK).show();
                txtBank.setText("");
            } else{
                new Alert(Alert.AlertType.ERROR, "Update failed!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadBanks();
    }
    
    
    @FXML
    private void tblBank_Clicked(MouseEvent event) {
        if(tblBanks.getSelectionModel().getSelectedIndex() == -1){
            txtBank.setText("");
            return;
        }
        BankTM bank = tblBanks.getSelectionModel().getSelectedItem();
        txtBank.setText(bank.getBankName());
    }

    @FXML
    private void tblBranch_Clicked(MouseEvent event) {
        BranchTM selectedItem = tblBranch.getSelectionModel().getSelectedItem();
        txtBranch.setText(selectedItem.getBranchName());
        cmbBank.getSelectionModel().select(selectedItem.getBankName());
    }

    @FXML
    private void branch_Remove(ActionEvent event) {
        if(tblBranch.getSelectionModel().getSelectedIndex() == -1)
            return;
        Alert alert  = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete the branch?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO)
            return;
        try {
            boolean result = branchBO.deleteBranch(tblBranch.getSelectionModel().getSelectedItem().getBranchid());
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Deleted!", ButtonType.OK).show();
                txtBranch.setText("");
                cmbBank.getSelectionModel().clearSelection();
            }else{
                new Alert(Alert.AlertType.ERROR, "Delete failed!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadBranch();
    }

    @FXML
    private void branch_Edit(ActionEvent event) {
        if(tblBranch.getSelectionModel().getSelectedIndex() ==-1){
            return;
        }
        BranchTM branchTM = tblBranch.getSelectionModel().getSelectedItem();
        try {
            boolean update = branchBO.updateBranch(new BranchDTO(branchTM.getBranchid(), -1, txtBranch.getText(), cmbBank.getValue()));
            if (update) {
                new Alert(Alert.AlertType.INFORMATION, "Updated!", ButtonType.OK).show();
                txtBranch.setText("");
                cmbBank.getSelectionModel().clearSelection();
            }else{
                new Alert(Alert.AlertType.ERROR, "Updating failed", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadBranch();
    }

    @FXML
    private void branch_Add(ActionEvent event) {
        if(txtBranch.getText().equals("") || cmbBank.getSelectionModel().getSelectedIndex() ==-1)
            return;
        try {
            boolean result = branchBO.saveBranch(new BranchDTO(-1, -1, txtBranch.getText(), cmbBank.getValue()));
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Branch added successfully!", ButtonType.OK).show();
                txtBranch.setText("");
                cmbBank.getSelectionModel().clearSelection();
            } else{
                new Alert(Alert.AlertType.ERROR, "Adding branch failed!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadBranch();
    }
    private void loadBranch(){
        ArrayList<BranchTM> branchTMs = new ArrayList<>();
        try {
            ArrayList<BranchDTO> branches = branchBO.getBranches();
            for (BranchDTO branch : branches) {
                branchTMs.add(new BranchTM(branch.getBranchid(), branch.getBranchName(), branch.getBankName()));
            }
        } catch (Exception ex) {
            Logger.getLogger(BanksAndBranchesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblBranch.setItems(FXCollections.observableArrayList(branchTMs));
    }
}
