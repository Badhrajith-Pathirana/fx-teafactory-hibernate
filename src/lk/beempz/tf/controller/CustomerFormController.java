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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.dto.SupplierDTO;
import lk.beempz.tf.view.tblmodel.SupplierTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class CustomerFormController implements Initializable {

//    @FXML
//    private JFXTextField txtSupName;
//    @FXML
//    private JFXTextField txtContactNo;
//    @FXML
//    private JFXTextField txtAdress;
//    @FXML
//    private JFXComboBox<String> cmbRoute;
//    @FXML
//    private JFXButton btnAdd;
    @FXML
    private TableView<SupplierTM> tblSupplier;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnRefresh;
    @FXML
    private JFXTextField txtFind;
    @FXML
    private JFXButton btnFind;
    public static SupplierTM SUPPLIER= null;
    /**
     * Initializes the controller class.
     */
    SupplierBO supplierBO;
    RouteBO routeBO;
    ObservableList<SupplierTM> allitems;
    public CustomerFormController() {
        this.routeBO = (RouteBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROUTE);
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("supplierid"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("route"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        loadTable();
//        loadCmb();
    }

//    @FXML
//    private void btnAddOnAction(ActionEvent event) {
//        if (tblSupplier.getSelectionModel().getSelectedIndex() == -1) {
//            if (txtSupName.getText().equals("") || txtAdress.getText().equals("") || txtContactNo.getText().equals("") || cmbRoute.getSelectionModel().getSelectedIndex() == -1) {
//                return;
//            }
//            try {
//                boolean result = supplierBO.addNewSupplier(new SupplierDTO(-1, txtSupName.getText(), -1, cmbRoute.getValue(), txtContactNo.getText(), txtAdress.getText()));
//                if (result) {
//                    new Alert(Alert.AlertType.INFORMATION, "Supplier added successfully!", ButtonType.OK).show();
//                    txtSupName.setText("");
//                    txtAdress.setText("");
//                    txtContactNo.setText("");
//                    cmbRoute.getSelectionModel().clearSelection();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Supplier not added!", ButtonType.OK).show();
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } else {
//            if (txtSupName.getText().equals("") || txtAdress.getText().equals("") || txtContactNo.getText().equals("") || cmbRoute.getSelectionModel().getSelectedIndex() == -1) {
//                return;
//            }
//            try {
//                boolean result = supplierBO.updateSupplier(new SupplierDTO(tblSupplier.getSelectionModel().getSelectedItem().getSupplierid(), txtSupName.getText(), -1, cmbRoute.getValue(), txtContactNo.getText(), txtAdress.getText()));
//                if (result) {
//                    new Alert(Alert.AlertType.INFORMATION, "Supplier updated successfully!", ButtonType.OK).show();
//                    txtSupName.setText("");
//                    txtAdress.setText("");
//                    txtContactNo.setText("");
//                    cmbRoute.getSelectionModel().clearSelection();
//                    tblSupplier.getSelectionModel().clearSelection();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Supplier not updated!", ButtonType.OK).show();
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        loadTable();
//    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {

        if (tblSupplier.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete this supplier?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO) {
            return;
        }
        try {
            boolean delete = supplierBO.deleteSupplier(tblSupplier.getSelectionModel().getSelectedItem().getSupplierid());
            if (delete) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted successfully", ButtonType.OK).show();
//                txtSupName.setText("");
//                txtAdress.setText("");
//                txtContactNo.setText("");
//                cmbRoute.getSelectionModel().clearSelection();
                tblSupplier.getSelectionModel().clearSelection();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();

    }

    @FXML
    private void btnRefresh_OnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void btnFind_OnAction(ActionEvent event) {
        if(allitems == null)
            allitems = tblSupplier.getItems();
        String find = txtFind.getText();
        ArrayList<SupplierTM> newItems = new ArrayList<>();
        for (SupplierTM allitem : allitems) {
            if(allitem.getName().equals("*"+find+"*")){
                newItems.add(allitem);
            }
        }
        tblSupplier.setItems(FXCollections.observableArrayList(newItems));
        
        
    }

    private void loadTable() {
        ArrayList<SupplierTM> suppliers = new ArrayList<>();

        try {
            ArrayList<SupplierDTO> supplierDTOs = supplierBO.getSuppliers();
            supplierDTOs.forEach((supplierDTO) -> {
                suppliers.add(new SupplierTM(supplierDTO.getSupplierid(), supplierDTO.getName(), supplierDTO.getRoute(), supplierDTO.getContact(), supplierDTO.getAddress()));
            });
        } catch (Exception ex) {
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblSupplier.setItems(FXCollections.observableArrayList(suppliers));
    }

//    private void loadCmb() {
//        ArrayList<String> routesStr = new ArrayList<>();
//
//        try {
//            ArrayList<RouteDTO> routes = routeBO.getRoutes();
//            for (RouteDTO route : routes) {
//                routesStr.add(route.getRoute());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        cmbRoute.setItems(FXCollections.observableArrayList(routesStr));
//    }

    @FXML
    private void tbl_Clicked(MouseEvent event) {
        SupplierTM supplier = tblSupplier.getSelectionModel().getSelectedItem();
//        txtSupName.setText(supplier.getName());
//        txtAdress.setText(supplier.getAddress());
//        txtContactNo.setText(supplier.getContact());
//        cmbRoute.getSelectionModel().select(supplier.getRoute());
        this.SUPPLIER = supplier;
    }
}
