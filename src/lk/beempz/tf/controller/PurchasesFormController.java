/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.view.tblmodel.PurchaseTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class PurchasesFormController implements Initializable {

    @FXML
    private TableView<PurchaseTM> tblTransaction;
    PurchaseBO purchaseBO;

    public PurchasesFormController() {
        this.purchaseBO = (PurchaseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblTransaction.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        tblTransaction.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplierid"));
        tblTransaction.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("suppliername"));
        tblTransaction.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("aKg"));
        tblTransaction.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("bKg"));
        setTable();
    }

    private void setTable() {
        ArrayList<PurchaseTM> purchaseTMs = new ArrayList<>();
        try {
            ArrayList<PurchaseDTO> purchaseDTOs = purchaseBO.getAll();
            for (PurchaseDTO purchaseDTO : purchaseDTOs) {
                purchaseTMs.add(new PurchaseTM(purchaseDTO.getPurchaseid(), purchaseDTO.getDate(), purchaseDTO.getSupplierid(), purchaseDTO.getSuppliername(), purchaseDTO.getaKg(), purchaseDTO.getbKg()));

            }

        } catch (Exception ex) {
            Logger.getLogger(PurchasesFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblTransaction.setItems(FXCollections.observableArrayList(purchaseTMs));
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/AddNewTransaction.fxml"));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Add a purchase");
            newStage.showAndWait();
            setTable();
        } catch (IOException ex) {
            Logger.getLogger(PurchasesFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRemoveOnAction(ActionEvent event) {
        if (tblTransaction.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete this transaction", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO) {
            return;
        }
        try {
            boolean deletePurchase = purchaseBO.deletePurchase(tblTransaction.getSelectionModel().getSelectedItem().getPurchaseid(), tblTransaction.getSelectionModel().getSelectedItem().getPurchaseDate());
            if (deletePurchase) {
                new Alert(Alert.AlertType.INFORMATION, "Transaction deleted successfully", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(PurchasesFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable();
    }

}
