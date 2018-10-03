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
public class MonthlyRateDTO {
    private Date date;
    private BigDecimal aGrade;
    private BigDecimal bGrade;
    private BigDecimal travelling;

    public MonthlyRateDTO() {
    }

    public MonthlyRateDTO(Date date, BigDecimal aGrade, BigDecimal bGrade, BigDecimal travelling) {
        this.date = date;
        this.aGrade = aGrade;
        this.bGrade = bGrade;
        this.travelling = travelling;
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
     * @return the aGrade
     */
    public BigDecimal getaGrade() {
        return aGrade;
    }

    /**
     * @param aGrade the aGrade to set
     */
    public void setaGrade(BigDecimal aGrade) {
        this.aGrade = aGrade;
    }

    /**
     * @return the bGrade
     */
    public BigDecimal getbGrade() {
        return bGrade;
    }

    /**
     * @param bGrade the bGrade to set
     */
    public void setbGrade(BigDecimal bGrade) {
        this.bGrade = bGrade;
    }

    /**
     * @return the travelling
     */
    public BigDecimal getTravelling() {
        return travelling;
    }

    /**
     * @param travelling the travelling to set
     */
    public void setTravelling(BigDecimal travelling) {
        this.travelling = travelling;
    }
    
}
