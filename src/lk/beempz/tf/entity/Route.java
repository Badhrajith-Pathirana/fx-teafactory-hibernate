/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.entity;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author badhr
 */
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "routeGen")
    @TableGenerator(name = "routeGen",allocationSize = 1,initialValue = 0,valueColumnName = "current_id", table = "seq_table" ,pkColumnValue = "route",pkColumnName = "table_name")
    private int routeid;
    private String routename;
    @OneToMany(mappedBy = "route")
    private List<Supplier> suppliers;

    public Route() {
    }

    public Route(String routename) {
        this.routename = routename;
    }

    public Route(int routeid, String routename) {
        this.routeid = routeid;
        this.routename = routename;
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
     * @return the routename
     */
    public String getRoutename() {
        return routename;
    }

    /**
     * @param routename the routename to set
     */
    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
