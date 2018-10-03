/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author badhr
 */
@Embeddable
public class Supplier_Bank_PK implements Serializable {
    private int branchid;
    private int supplierid;

    public Supplier_Bank_PK() {
    }

    public Supplier_Bank_PK(int branchid, int supplierid) {
        this.branchid = branchid;
        this.supplierid = supplierid;
    }

    /**
     * @return the branchid
     */
    public int getBranchid() {
        return branchid;
    }

    /**
     * @param branchid the branchid to set
     */
    public void setBranchid(int branchid) {
        this.branchid = branchid;
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
    
}
