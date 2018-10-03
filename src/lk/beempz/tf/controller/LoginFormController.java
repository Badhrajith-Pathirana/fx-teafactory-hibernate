/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.dto.UserDTO;
import lk.beempz.tf.business.custom.UserBO;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class LoginFormController implements Initializable {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
    UserBO userBo;

    public LoginFormController() {
        this.userBo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void checkLogin() {
        try {
            boolean loginSuccess = userBo.loginSuccess(new UserDTO(txtUsername.getText(), txtPassword.getText()));
            if(loginSuccess){
                Parent root = FXMLLoader.load(getClass().getResource("/lk/beempz/tf/view/MainPageForm.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Tea Factory Management | Main");
//                primaryStage.setFullScreen(true);
//                primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
//                primaryStage.setFullScreenExitHint("Exiting..");
                primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        System.exit(0);
                    }
                });
                primaryStage.show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Wrong Username or password", ButtonType.OK).show();
                txtPassword.setText("");
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Sorry something went wrong.. Please try again!", ButtonType.OK).show();
            Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void loginClicked(ActionEvent event) {
        checkLogin();
    }

    @FXML
    private void passwordOnPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB){
            checkLogin();
        }
    }
}
