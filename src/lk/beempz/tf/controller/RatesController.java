/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import java.io.IOException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.view.tblmodel.RateTM;
import lk.beempz.tf.view.tblmodel.RouteTM;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class RatesController implements Initializable {

    @FXML
    private TableView<RateTM> tblRate;
    private MonthlyRateBO monthlyRateBO;
    private static MonthlyRateDTO monthlyRateDTO = null;
    public RatesController() {
        this.monthlyRateBO = (MonthlyRateBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MONTHLY_RATE);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        monthlyRateDTO = null;
        tblRate.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("month"));
        tblRate.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("transportRate"));
        tblRate.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("grdAtea"));
        tblRate.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("grdBtea"));
        setTable();
    }   
    private void setTable(){
        ArrayList<RateTM> rateTMs = new ArrayList<>();
        try {
            ArrayList<MonthlyRateDTO> rateDTOs = monthlyRateBO.getAllRates();
            for (MonthlyRateDTO rateDTO : rateDTOs) {
                rateTMs.add(new RateTM(rateDTO.getDate(), rateDTO.getTravelling(), rateDTO.getaGrade(), rateDTO.getbGrade()));
            }
        } catch (Exception ex) {
            Logger.getLogger(RatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblRate.setItems(FXCollections.observableArrayList(rateTMs));
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/AddEditRate.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            //primaryStage.setAlwaysOnTop(true);
            primaryStage.showAndWait();
            setTable();
        } catch (IOException ex) {
            Logger.getLogger(RatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btnEdit_OnAction(ActionEvent event) {
        if(tblRate.getSelectionModel().getSelectedIndex() == -1)
            return;
        RateTM selectedItem = tblRate.getSelectionModel().getSelectedItem();
        monthlyRateDTO = new MonthlyRateDTO(selectedItem.getMonth(), selectedItem.getGrdAtea(), selectedItem.getGrdBtea(), selectedItem.getTransportRate());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/AddEditRate.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            //primaryStage.setAlwaysOnTop(true);
            primaryStage.showAndWait();
            setTable();
        } catch (IOException ex) {
            Logger.getLogger(RatesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static MonthlyRateDTO getMonthlyRateDTO(){
        return monthlyRateDTO;
    }
}
