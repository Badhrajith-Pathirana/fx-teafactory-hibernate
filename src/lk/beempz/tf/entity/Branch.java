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
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "branchGen")
    @TableGenerator(name = "branchGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "branch",pkColumnName = "table_name")
    private int branchid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankid",referencedColumnName = "bankid")
    private Bank bank;
    private String branchName;
    @OneToMany(mappedBy = "branch", cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Supplier_Bank> supplier_banks = new ArrayList<>();

    public Branch() {
    }

    public Branch(Bank bank, String branchName) {
        this.bank = bank;
        this.branchName = branchName;
    }

    public Branch(int branchid, Bank bank, String branchName) {
        this.branchid = branchid;
        this.setBank(bank);
        this.branchName = branchName;
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


    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Supplier_Bank> getSupplier_banks() {
        return supplier_banks;
    }

    public void setSupplier_banks(List<Supplier_Bank> supplier_banks) {
        this.supplier_banks = supplier_banks;
    }
}
