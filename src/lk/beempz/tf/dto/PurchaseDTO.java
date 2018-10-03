/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author badhr
 */
public class PurchaseDTO {
    private int purchaseid;
    private Date date;
    private int supplierid;
    private String suppliername;
    private BigDecimal aKg;
    private BigDecimal bKg;

    public PurchaseDTO(int purchaseid, Date date, int supplierid, String suppliername, BigDecimal aKg, BigDecimal bKg) {
        this.purchaseid = purchaseid;
        this.date = date;
        this.supplierid = supplierid;
        this.suppliername = suppliername;
        this.aKg = aKg;
        this.bKg = bKg;
    }

    public PurchaseDTO() {
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
    public BigDecimal getaKg() {
        return aKg;
    }

    /**
     * @param aKg the aKg to set
     */
    public void setaKg(BigDecimal aKg) {
        this.aKg = aKg;
    }

    /**
     * @return the bKg
     */
    public BigDecimal getbKg() {
        return bKg;
    }

    /**
     * @param bKg the bKg to set
     */
    public void setbKg(BigDecimal bKg) {
        this.bKg = bKg;
    }
}
