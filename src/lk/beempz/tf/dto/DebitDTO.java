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
public class DebitDTO {
    private int debitid;
    private Date date;
    private int purchaseid;
    private int supplierid;
    private String name;
    private BigDecimal amount;

    public DebitDTO() {
    }

    public DebitDTO(int debitid, Date date, int purchaseid, int supplierid, String name, BigDecimal amount) {
        this.debitid = debitid;
        this.date = date;
        this.purchaseid = purchaseid;
        this.supplierid = supplierid;
        this.name = name;
        this.amount = amount;
    }

    /**
     * @return the debitid
     */
    public int getDebitid() {
        return debitid;
    }

    /**
     * @param debitid the debitid to set
     */
    public void setDebitid(int debitid) {
        this.debitid = debitid;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
}
