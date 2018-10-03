/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import lk.beempz.tf.dao.CrudDAOImpl;
import lk.beempz.tf.dao.custom.RouteDAO;
import lk.beempz.tf.entity.Route;


public class RouteDAOImpl extends CrudDAOImpl<Route,Integer> implements RouteDAO {

    @Override
    public Route getRouteID(String name) {
        return session.createQuery("FROM Route WHERE routename = ?1",Route.class).setParameter(1,name).getSingleResult();
    }
}
