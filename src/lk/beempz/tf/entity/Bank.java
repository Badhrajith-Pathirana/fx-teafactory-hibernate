/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author badhr
 */
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "bankGen")
    @TableGenerator(name = "bankGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "bank",pkColumnName = "table_name")
    private int bankid;
    private String bankName;

    @OneToMany(mappedBy = "bank",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Branch> branches = new ArrayList<>();


    public Bank() {
    }

    public Bank(int bankid, String bankName) {
        this.bankid = bankid;
        this.bankName = bankName;
    }
    public Bank( String bankName) {
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
     * @return the bankname
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankname the bankname to set
     */
    public void setBankname(String bankName) {
        this.bankName = bankName;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
