/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dto;

/**
 *
 * @author beempz
 */
public class Supplier_BankDTO {
    private int branchid;
    private int supplierid;
    private String acc_no;

    public Supplier_BankDTO() {
    }

    public Supplier_BankDTO(int branchid, int supplierid, String acc_no) {
        this.branchid = branchid;
        this.supplierid = supplierid;
        this.acc_no = acc_no;
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
     * @return the bankid
     */
    public int getSupplierid() {
        return supplierid;
    }

    /**
     * @param bankid the bankid to set
     */
    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
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
}