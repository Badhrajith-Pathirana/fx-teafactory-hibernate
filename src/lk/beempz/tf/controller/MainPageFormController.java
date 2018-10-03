/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class MainPageFormController implements Initializable {

    @FXML
    private Pane paneCustomer;
    @FXML
    private AnchorPane root;
    @FXML
    private Pane paneBanks;
    @FXML
    private Pane panePayment;
    @FXML
    private Pane panePurchase;
    @FXML
    private Pane paneRoutes;
    @FXML
    private Pane paneRates;
    @FXML
    private Pane paneReports;
    @FXML
    private Pane paneAccounts;
    @FXML
    private Pane paneSettings;
    @FXML
    private Pane paneSettings2;
    @FXML
    private Pane paneSettings1;
    @FXML
    private Pane paneExit;
    @FXML
    private Pane paneRoutes1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), root);
        tt.setFromX(0);
        tt.setToX(1.0);
        tt.play();
    }

    @FXML
    private void onIconEntered(MouseEvent event) {
        if (event.getSource() instanceof Pane) {
            Pane pane = (Pane) event.getSource();
            ScaleTransition st = new ScaleTransition(Duration.millis(200), pane);
            st.setFromX(1.0);
            st.setFromY(1.0);
            st.setToX(1.3);
            st.setToY(1.3);
            st.play();
        }
    }

    @FXML
    private void onIconClicked(MouseEvent event) {
        Parent root = null;
        String title = "TeaFactory | ";
        if (event.getSource() instanceof Pane) {
            try {
                Pane pane = (Pane) event.getSource();
                switch (pane.getId()) {
                    case "paneCustomer":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/CustomerForm.fxml"));
                        title += "Customer Details";
                        break;
                    case "paneBanks":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/BanksAndBranches.fxml"));
                        title += "Bank Details";
                        break;
                    case "panePayment":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/SupplierAccounts.fxml"));
                        title += "Supplier Details";
                        break;
                    case "panePurchase":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/PurchasesForm.fxml"));
                        title += "Purchases";
                        break;
                    case "paneRoutes":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/Routes.fxml"));
                        title += "Routes";
                        break;
                    case "paneReports":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/Banks.fxml"));
                        title += "Reports";
                        break;
                    case "paneRates":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/Rates.fxml"));
                        title += "Monthly Rates";
                        break;
                    case "paneAccounts":
                        root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/Reports.fxml"));
                        title += "Accounts";
                        break;
                    case "paneExit":
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you need to exit?", ButtonType.YES,ButtonType.NO);
                        alert.showAndWait();
                        if(alert.getResult() == ButtonType.NO)
                            return;
                        System.exit(0);
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(MainPageFormController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (root == null) {
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.showAndWait();
    }

    @FXML
    private void onIconExited(MouseEvent event) {
        if (event.getSource() instanceof Pane) {
            Pane pane = (Pane) event.getSource();
            ScaleTransition st = new ScaleTransition(Duration.millis(200), pane);
            st.setFromX(1.3);
            st.setFromY(1.3);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        }
    }

}
