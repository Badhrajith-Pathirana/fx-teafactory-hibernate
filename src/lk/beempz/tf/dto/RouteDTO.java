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
public class RouteDTO {
    private int routeid;
    private String route;

    public RouteDTO() {
    }

    public RouteDTO(int routeid, String route) {
        this.routeid = routeid;
        this.route = route;
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
    
}
