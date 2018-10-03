/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.view.tblmodel;

/**
 *
 * @author badhr
 */
public class BankTM {
    private int bankid;
    private String bankName;

    public BankTM() {
    }

    public BankTM(int bankid, String bankName) {
        this.bankid = bankid;
        this.bankName = bankName;
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
