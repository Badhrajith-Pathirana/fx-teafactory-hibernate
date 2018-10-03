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
public class CreditTM {
    private int creditid;
    private Date date;
    private int supplierno;
    private String name;
    private String description;
    private BigDecimal amount;

    public CreditTM() {
    }
    
    public CreditTM(int creditid, Date date, int supplierno, String name, String description, BigDecimal amount) {
        this.creditid = creditid;
        this.date = date;
        this.supplierno = supplierno;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    /**
     * @return the creditid
     */
    public int getCreditid() {
        return creditid;
    }

    /**
     * @param creditid the creditid to set
     */
    public void setCreditid(int creditid) {
        this.creditid = creditid;
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
     * @return the supplierno
     */
    public int getSupplierno() {
        return supplierno;
    }

    /**
     * @param supplierno the supplierno to set
     */
    public void setSupplierno(int supplierno) {
        this.supplierno = supplierno;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
