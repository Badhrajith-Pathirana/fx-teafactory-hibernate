/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lk.beempz.tf.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author badhr
 */
public class BanksController implements Initializable {

    @FXML
    private JFXDatePicker dtFrom;
    @FXML
    private JFXDatePicker dtTo;
    @FXML
    private JFXComboBox<String> cmbReport;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCmb();
        
    }    
    private void setCmb(){
        ArrayList<String> reports = new ArrayList<>();
        reports.add("Purchase Report");
        reports.add("Bank Report");
        cmbReport.setItems(FXCollections.observableArrayList(reports));
    }
    @FXML
    private void btnPrint_OnAction(ActionEvent event) {
        InputStream strm = getInput(cmbReport.getValue());
        HashMap map = setHashes();
        try {
            JasperPrint fillReport = JasperFillManager.fillReport(strm, map,DBConnection.getInstance().getConnection());
            JasperPrintManager.printReport(fillReport, true);
        } catch (ClassNotFoundException | SQLException | JRException | IOException  ex) {
            Logger.getLogger(BanksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private InputStream getInput(String input){
        String path = "";
        switch(input){
            case "Purchase Report":
                path = "/lk/beempz/tf/reports/PurchasesReports_1.jasper";
                break;
            case "Bank Report":
                path = "/lk/beempz/tf/reports/Bank_1.jasper";
                break;
                default:
                    return null;
        }
        InputStream strm = getClass().getResourceAsStream(path);
        return strm;
    }
    private HashMap setHashes(){
        HashMap map = new HashMap();
        map.put("fromdate", Date.from(dtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        map.put("todate", Date.from(dtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return map;
    }

    @FXML
    private void btnView_OnAction(ActionEvent event) {
        InputStream strm = getInput(cmbReport.getValue());
        HashMap map = setHashes();
        try {
            JasperPrint fillReport = JasperFillManager.fillReport(strm, map,DBConnection.getInstance().getConnection());
            File pdf = File.createTempFile("output.", ".pdf");
            JasperViewer.viewReport(fillReport, false);
            JasperExportManager.exportReportToPdfStream(fillReport,new FileOutputStream(pdf));
        } catch (ClassNotFoundException | SQLException | JRException | IOException ex) {
            Logger.getLogger(BanksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
