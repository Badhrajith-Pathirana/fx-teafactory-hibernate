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
public class RateTM {
    private Date month;
    private BigDecimal transportRate;
    private BigDecimal grdAtea;
    private BigDecimal grdBtea;

    public RateTM() {
    }

    public RateTM(Date month, BigDecimal transportRate, BigDecimal grdAtea, BigDecimal grdBtea) {
        this.month = month;
        this.transportRate = transportRate;
        this.grdAtea = grdAtea;
        this.grdBtea = grdBtea;
    }

    /**
     * @return the month
     */
    public Date getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(Date month) {
        this.month = month;
    }

    /**
     * @return the transportRate
     */
    public BigDecimal getTransportRate() {
        return transportRate;
    }

    /**
     * @param transportRate the transportRate to set
     */
    public void setTransportRate(BigDecimal transportRate) {
        this.transportRate = transportRate;
    }

    /**
     * @return the grdAtea
     */
    public BigDecimal getGrdAtea() {
        return grdAtea;
    }

    /**
     * @param grdAtea the grdAtea to set
     */
    public void setGrdAtea(BigDecimal grdAtea) {
        this.grdAtea = grdAtea;
    }

    /**
     * @return the grdBtea
     */
    public BigDecimal getGrdBtea() {
        return grdBtea;
    }

    /**
     * @param grdBtea the grdBtea to set
     */
    public void setGrdBtea(BigDecimal grdBtea) {
        this.grdBtea = grdBtea;
    }
    
}
