/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import javax.persistence.*;

/**
 *
 * @author badhr
 */
@Entity
public class Supplier_Bank {
    @EmbeddedId
    private Supplier_Bank_PK supplier_Bank_PK;
    private String acc_no;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierid",referencedColumnName = "supplierno",updatable = false,insertable = false)
    private
    Supplier supplier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchid", referencedColumnName = "branchid",updatable = false, insertable = false)
    private
    Branch branch;
    public Supplier_Bank() {
    }


    public Supplier_Bank(Supplier_Bank_PK supplier_Bank_PK, String acc_no) {
        this.supplier_Bank_PK = supplier_Bank_PK;
        this.acc_no = acc_no;
    }

    public Supplier_Bank(int bankid,int supplierid, String acc_no) {
        this.supplier_Bank_PK = new Supplier_Bank_PK(bankid,supplierid);
        this.acc_no = acc_no;
    }


    /**
     * @return the supplier_Bank_PK
     */
    public Supplier_Bank_PK getSupplier_Bank_PK() {
        return supplier_Bank_PK;
    }

    /**
     * @param supplier_Bank_PK the supplier_Bank_PK to set
     */
    public void setSupplier_Bank_PK(Supplier_Bank_PK supplier_Bank_PK) {
        this.supplier_Bank_PK = supplier_Bank_PK;
    }

    /**
     * @return the acc_no
     */
    public String getAcc_no() {
        return acc_no;
    }

    /**
     * @param acc_no the acc_no to set
     */
    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
