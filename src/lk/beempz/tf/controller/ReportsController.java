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
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.dto.SupplierDTO;
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
public class ReportsController implements Initializable {

    @FXML
    private JFXDatePicker dtFrom;
    @FXML
    private JFXDatePicker dtTo;
    @FXML
    private JFXComboBox<String> cmbReport;
    @FXML
    private JFXComboBox<String> cmbSupplier;
    SupplierBO supplierBO;

    public ReportsController() {
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCmbs();
    }    
    private void setCmbs(){
        ArrayList<String> suppliers = new ArrayList<>();
        ArrayList<String> reports = new ArrayList<>();
        
        try {
            ArrayList<SupplierDTO> suppliers1 = supplierBO.getSuppliers();
            for (SupplierDTO supplierDTO : suppliers1) {
                suppliers.add(supplierDTO.getSupplierid()+"");
            }
            reports.add("Credit Report");
            reports.add("Debit Report");
            
        } catch (Exception ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbSupplier.setItems(FXCollections.observableArrayList(suppliers));
        cmbReport.setItems(FXCollections.observableArrayList(reports));
    }

    @FXML
    private void btnPrint_OnAction(ActionEvent event) {
        InputStream strm = getInput(cmbReport.getValue());
        HashMap map = setHashes();
        try {
            JasperPrint fillReport = JasperFillManager.fillReport(strm, map,DBConnection.getInstance().getConnection());
            JasperPrintManager.printReport(fillReport, true);
        } catch (ClassNotFoundException | SQLException | JRException  ex) {
            Logger.getLogger(BanksController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private InputStream getInput(String input){
        String path = "";
        switch(input){
            case "Credit Report":
                path = "/lk/beempz/tf/reports/CreditReport.jasper";
                break;
            case "Debit Report":
                path = "/lk/beempz/tf/reports/DebitReport.jasper";
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
        map.put("supid", Integer.parseInt(cmbSupplier.getValue()));
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
