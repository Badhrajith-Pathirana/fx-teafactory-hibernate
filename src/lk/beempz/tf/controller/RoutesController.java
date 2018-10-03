/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

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
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.view.tblmodel.RouteTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class RoutesController implements Initializable {

    @FXML
    private JFXTextField txtRoute;
    @FXML
    private TableView<RouteTM> tblRoute;
    private RouteBO routeBO;

    public RoutesController() {
        this.routeBO = (RouteBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROUTE);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblRoute.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("routeid"));
        tblRoute.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("routename"));
        setTable();
    }    
    private void setTable(){
        ArrayList<RouteTM> routeTMs = new ArrayList<>();
        try {
            ArrayList<RouteDTO> routes = routeBO.getRoutes();
            for (RouteDTO route : routes) {
                routeTMs.add(new RouteTM(route.getRouteid(), route.getRoute()));
            }
        } catch (Exception ex) {
            Logger.getLogger(RoutesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblRoute.setItems(FXCollections.observableArrayList(routeTMs));
    }
    @FXML
    private void btnInsert_OnAction(ActionEvent event) {
        if(txtRoute.getText().equals(""))
            return;
        try {
            boolean result = routeBO.saveRoute(new RouteDTO(-1, txtRoute.getText()));
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Route added successfully!", ButtonType.OK).show();
                clearData();
            }else{
                new Alert(Alert.AlertType.ERROR, "Route adding failed!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(RoutesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable();
    }
    private void clearData(){
        txtRoute.setText("");
        tblRoute.getSelectionModel().clearSelection();
   }

    @FXML
    private void btnUpdate_OnAction(ActionEvent event) {
        if (txtRoute.getText().equals("")) {
            return;
        }
        if (tblRoute.getSelectionModel().getSelectedIndex() ==-1) {
            return;
        }
        try {
            boolean res = routeBO.updateRoute(new RouteDTO(tblRoute.getSelectionModel().getSelectedItem().getRouteid(), txtRoute.getText()));
            if (res) {
                new Alert(Alert.AlertType.INFORMATION, "Updated!", ButtonType.OK).show();
                clearData();
            }else{
                new Alert(Alert.AlertType.ERROR, "Not updated!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(RoutesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable();
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        if(tblRoute.getSelectionModel().getSelectedIndex() == -1)
            return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete the route?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO)
            return;
        try {
            boolean res = routeBO.deleteRoute(tblRoute.getSelectionModel().getSelectedItem().getRouteid());
            if (res) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!", ButtonType.OK).show();
            } else{
                new Alert(Alert.AlertType.ERROR, "Delete failed!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).show();
            Logger.getLogger(RoutesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTable();
        clearData();
    }
    
}
