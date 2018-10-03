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
public class Debit {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "debitGen")
    @TableGenerator(name = "debitGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "debit",pkColumnName = "table_name")
    private int debitid;
//    private int purchaseid;
    @Temporal(TemporalType.DATE)
    private Date debitdate;
//    private int supplierid;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "purchaseid", referencedColumnName = "purchase_id")
    private Purchase purchase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierid",referencedColumnName = "supplierno")
    private Supplier supplier;

    public Debit() {
    }

    public Debit(Date debitdate, BigDecimal amount, Purchase purchase, Supplier supplier) {
        this.debitdate = debitdate;
        this.amount = amount;
        this.purchase = purchase;
        this.supplier = supplier;
    }

    public Debit(int debitid, Purchase purchase, Date debitdate, Supplier supplier, BigDecimal amount) {
        this.debitid = debitid;
        this.purchase = purchase;
        this.debitdate = debitdate;
        this.supplier = supplier;
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
     * @return the debitdate
     */
    public Date getDebitdate() {
        return debitdate;
    }

    /**
     * @param debitdate the debitdate to set
     */
    public void setDebitdate(Date debitdate) {
        this.debitdate = debitdate;
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


    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
