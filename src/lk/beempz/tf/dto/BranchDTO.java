/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dto;

/**
 *
 * @author badhr
 */
public class BranchDTO {
    private int branchid;
    private int bankid;
    private String branchName;
    private String bankName;

    public BranchDTO() {
    }

    public BranchDTO(int branchid, int bankid, String branchName, String bankName) {
        this.branchid = branchid;
        this.bankid = bankid;
        this.branchName = branchName;
        this.bankName = bankName;
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
    public int getBankid() {
        return bankid;
    }

    /**
     * @param bankid the bankid to set
     */
    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
}
