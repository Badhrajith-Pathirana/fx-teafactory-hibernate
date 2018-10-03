/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.view.tblmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author badhr
 */
public class PurchaseTM {
    private int purchaseid;
    private Date purchaseDate;
    private int supplierid;
    private String suppliername;
    private BigDecimal aKg;
    private BigDecimal bKg;

    public PurchaseTM() {
    }

    public PurchaseTM(int purchaseid, Date purchaseDate, int supplierid, String suppliername, BigDecimal aKg, BigDecimal bKg) {
        this.purchaseid = purchaseid;
        this.purchaseDate = purchaseDate;
        this.supplierid = supplierid;
        this.suppliername = suppliername;
        this.aKg = aKg;
        this.bKg = bKg;
    }

    /**
     * @return the purchaseid
     */
    public int getPurchaseid() {
        return purchaseid;
    }

    /**
     * @param purchaseid the purchaseid to set
     */
    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the supplierid
     */
    public int getSupplierid() {
        return supplierid;
    }

    /**
     * @param supplierid the supplierid to set
     */
    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    /**
     * @return the suppliername
     */
    public String getSuppliername() {
        return suppliername;
    }

    /**
     * @param suppliername the suppliername to set
     */
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    /**
     * @return the aKg
     */
    public BigDecimal getAKg() {
        return aKg;
    }

    /**
     * @param aKg the aKg to set
     */
    public void setAKg(BigDecimal aKg) {
        this.aKg = aKg;
    }

    /**
     * @return the bKg
     */
    public BigDecimal getBKg() {
        return bKg;
    }

    /**
     * @param bKg the bKg to set
     */
    public void setbKg(BigDecimal bKg) {
        this.bKg = bKg;
    }

    
    
}
