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
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "supplierGen")
    @TableGenerator(name = "supplierGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "supplier",pkColumnName = "table_name")
    private int supplierno;
    private String name;
//    private int route;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<Credit> credits = new ArrayList<>();

    @OneToMany(mappedBy = "supplier")
    private
    List<Debit> debits;

    public Supplier(String name, String phone, String address, Route route) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.route = route;
    }

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "route", referencedColumnName = "routeid")
    private Route route;

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Supplier_Bank> supplier_banks = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(int supplierno, String name, Route route, String phone, String address) {
        this.supplierno = supplierno;
        this.name = name;
        this.setRoute(route);
        this.phone = phone;
        this.address = address;
    }

    /**
     * @return the supplierno
     */
    public int getSupplierno() {
        return supplierno;
    }

    /**
     * @param supplierno the supplierno to set
     */
    public void setSupplierno(int supplierno) {
        this.supplierno = supplierno;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<Debit> getDebits() {
        return debits;
    }

    public void setDebits(List<Debit> debits) {
        this.debits = debits;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Supplier_Bank> getSupplier_banks() {
        return supplier_banks;
    }

    public void setSupplier_banks(List<Supplier_Bank> supplier_banks) {
        this.supplier_banks = supplier_banks;
    }
}
