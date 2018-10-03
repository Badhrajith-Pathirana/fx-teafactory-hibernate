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
public class SupplierDTO {
    private int supplierid;
    private String name;
    private int routeid;
    private String route;
    private String contact;
    private String address;

    public SupplierDTO() {
    }

    public SupplierDTO(int supplierid, String name, int routeid, String route, String contact, String address) {
        this.supplierid = supplierid;
        this.name = name;
        this.routeid = routeid;
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
     * @return the routeid
     */
    public int getRouteid() {
        return routeid;
    }

    /**
     * @param routeid the routeid to set
     */
    public void setRouteid(int routeid) {
        this.routeid = routeid;
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
