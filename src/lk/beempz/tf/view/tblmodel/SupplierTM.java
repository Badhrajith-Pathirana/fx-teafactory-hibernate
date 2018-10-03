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
public class SupplierTM {
    private int supplierid;
    private String name;
    private String route;
    private String contact;
    private String address;

    public SupplierTM() {
    }

    public SupplierTM(int supplierid, String name, String route, String contact, String address) {
        this.supplierid = supplierid;
        this.name = name;
        this.route = route;
        this.contact = contact;
        this.address = address;
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
     * @return the route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
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
    
}
