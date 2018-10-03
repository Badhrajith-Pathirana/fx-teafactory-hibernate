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
public class CreditDTO {
    private int creditid;
    private int supplierid;
    private String suppliername;
    private int creditType;
    private String credit_typename;
    private Date date;
    private BigDecimal amount;

    public CreditDTO() {
    }

    public CreditDTO(int creditid, int supplierid, String suppliername, int creditType, String credit_typename, Date date, BigDecimal amount) {
        this.creditid = creditid;
        this.supplierid = supplierid;
        this.suppliername = suppliername;
        this.creditType = creditType;
        this.credit_typename = credit_typename;
        this.date = date;
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
     * @return the creditType
     */
    public int getCreditType() {
        return creditType;
    }

    /**
     * @param creditType the creditType to set
     */
    public void setCreditType(int creditType) {
        this.creditType = creditType;
    }

    /**
     * @return the credit_typename
     */
    public String getCredit_typename() {
        return credit_typename;
    }

    /**
     * @param credit_typename the credit_typename to set
     */
    public void setCredit_typename(String credit_typename) {
        this.credit_typename = credit_typename;
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
