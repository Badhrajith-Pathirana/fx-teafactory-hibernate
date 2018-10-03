/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.RouteDTO;

/**
 *
 * @author badhr
 */
public interface RouteBO extends SuperBO{
    public RouteDTO findRoute(int routeid)throws Exception;
    public ArrayList<RouteDTO> getRoutes() throws Exception;
    public RouteDTO getRouteByName(String name)throws Exception;
    public boolean saveRoute(RouteDTO routeDTO)throws Exception;
    public boolean updateRoute(RouteDTO routeDTO)throws Exception;
    public boolean deleteRoute(int routeid)throws Exception;
}
