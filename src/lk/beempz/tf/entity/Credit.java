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
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "creditGen")
    @TableGenerator(name = "creditGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "credit",pkColumnName = "table_name")
    private int creditid;
//    private int credit_type;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    private BigDecimal amount;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierid", referencedColumnName = "supplierno")
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_type", referencedColumnName = "typeid")
    private Credit_Type credit_type;

    public Credit() {
    }

    public Credit(Date date, BigDecimal amount, Supplier supplier, Credit_Type credit_type) {
        this.date = date;
        this.amount = amount;
        this.supplier = supplier;
        this.credit_type = credit_type;
    }

    public Credit(int creditid, Supplier supplier, Credit_Type credit_type, Date date, BigDecimal amount) {
        this.creditid = creditid;
        this.setSupplier(supplier);
        this.setCredit_type(credit_type);
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Credit_Type getCredit_type() {
        return credit_type;
    }

    public void setCredit_type(Credit_Type credit_type) {
        this.credit_type = credit_type;
    }
}
