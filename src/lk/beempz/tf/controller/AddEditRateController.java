/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.MonthlyRateNewBO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class AddEditRateController implements Initializable {

    @FXML
    private JFXDatePicker dpDate;
    @FXML
    private JFXTextField txtAkg;
    @FXML
    private JFXTextField txtBKg;
    @FXML
    private JFXTextField txtTravel;
    private boolean update;
    MonthlyRateNewBO monthlyNewRateBO;
    MonthlyRateBO monthlyRateBO;
    MonthlyRateDTO monthlyRateDTO;
    @FXML
    private AnchorPane root;

    public AddEditRateController() {
        this.monthlyRateBO = (MonthlyRateBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MONTHLY_RATE);
        this.monthlyRateDTO = RatesController.getMonthlyRateDTO();
        System.out.println(monthlyRateDTO);
        this.monthlyNewRateBO = (MonthlyRateNewBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MONTHLY_RATE_NEW);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (monthlyRateDTO == null) {
            update = false;
        } else {
            update = true;
//            dpDate.setValue(monthlyRateDTO.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            txtAkg.setText(monthlyRateDTO.getaGrade().toString());
            txtBKg.setText(monthlyRateDTO.getbGrade().toString());
            txtTravel.setText(monthlyRateDTO.getTravelling().toString());
        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {
//        if (!txtAkg.getText().matches("\\d*\\.?\\d{0,2}") || !txtBKg.getText().matches("\\d*\\.?\\d{0,2}") || !txtTravel.getText().matches("\\d*\\.?\\d{0,2}")) {
//            System.out.println("Noentr");
//            return;
//        }
        if (update) {
            try {
                boolean result = monthlyNewRateBO.insertMonthlyRates(new MonthlyRateDTO(monthlyRateDTO.getDate(), new BigDecimal(txtAkg.getText()), new BigDecimal(txtBKg.getText()), new BigDecimal(txtTravel.getText())));
                if (result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Updated successfully!", ButtonType.OK);
                    alert.showAndWait();
                    closeStage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update failed!", ButtonType.OK).show();
                }

            } catch (Exception ex) {
                Logger.getLogger(AddEditRateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                MonthlyRateDTO rate = monthlyNewRateBO.findByID(Date.from(dpDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if(rate != null){
                    new Alert(Alert.AlertType.ERROR, "The rate already inserted", ButtonType.OK).show();
                    return;
                }
                boolean result = monthlyRateBO.updateMonthlyRates(new MonthlyRateDTO(Date.from(dpDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), new BigDecimal(txtAkg.getText()), new BigDecimal(txtBKg.getText()), new BigDecimal(txtTravel.getText())));
                if (result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data Inserted successfully!", ButtonType.OK);
                    alert.showAndWait();
                    closeStage();
                }
            } catch (Exception ex) {
                Logger.getLogger(AddEditRateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void closeStage() {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();
    }

}
