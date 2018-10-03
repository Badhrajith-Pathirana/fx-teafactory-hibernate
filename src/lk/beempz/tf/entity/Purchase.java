/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author badhr
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchaseGen")
    @TableGenerator(name = "purchaseGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "purchase",pkColumnName = "table_name")
    private int purchase_id;
    @Temporal(value = TemporalType.DATE)
    private Date purchase_date;
//    private int supplierid;
    private BigDecimal akg;
    private BigDecimal bkg;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE} ,fetch = FetchType.LAZY , mappedBy = "purchase")
    private Debit debit;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "supplierid",referencedColumnName = "supplierno")
    private Supplier supplier;

    public Purchase() {
    }

    public Purchase(Date purchase_date,Supplier supplier, BigDecimal akg, BigDecimal bkg) {
        this.purchase_date = purchase_date;
        this.supplier = supplier;
        this.akg = akg;
        this.bkg = bkg;
    }

    public Purchase(int purchase_id, Date purchase_date, Supplier supplier, BigDecimal akg, BigDecimal bkg) {
        this.purchase_id = purchase_id;
        this.purchase_date = purchase_date;
        this.supplier = supplier;
        this.akg = akg;
        this.bkg = bkg;
    }

    /**
     * @return the purchase_id
     */
    public int getPurchase_id() {
        return purchase_id;
    }

    /**
     * @param purchase_id the purchase_id to set
     */
    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    /**
     * @return the purchase_date
     */
    public Date getPurchase_date() {
        return purchase_date;
    }

    /**
     * @param purchase_date the purchase_date to set
     */
    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    /**
     * @return the akg
     */
    public BigDecimal getAkg() {
        return akg;
    }

    /**
     * @param akg the akg to set
     */
    public void setAkg(BigDecimal akg) {
        this.akg = akg;
    }

    /**
     * @return the bkg
     */
    public BigDecimal getBkg() {
        return bkg;
    }

    /**
     * @param bkg the bkg to set
     */
    public void setBkg(BigDecimal bkg) {
        this.bkg = bkg;
    }

    public Debit getDebit() {
        return debit;
    }

    public void setDebit(Debit debit) {
        this.debit = debit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
