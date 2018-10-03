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
public class UnprocessedDebitDTO {
    private Date date;
    private int supplierid;
    private String description;
    private BigDecimal aGrd;
    private BigDecimal bGrd;

    public UnprocessedDebitDTO() {
    }

    public UnprocessedDebitDTO(Date date, int supplierid, String description, BigDecimal aGrd, BigDecimal bGrd) {
        this.date = date;
        this.supplierid = supplierid;
        this.description = description;
        this.aGrd = aGrd;
        this.bGrd = bGrd;
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
     * @return the aGrd
     */
    public BigDecimal getaGrd() {
        return aGrd;
    }

    /**
     * @param aGrd the aGrd to set
     */
    public void setaGrd(BigDecimal aGrd) {
        this.aGrd = aGrd;
    }

    /**
     * @return the bGrd
     */
    public BigDecimal getbGrd() {
        return bGrd;
    }

    /**
     * @param bGrd the bGrd to set
     */
    public void setbGrd(BigDecimal bGrd) {
        this.bGrd = bGrd;
    }

    
}
