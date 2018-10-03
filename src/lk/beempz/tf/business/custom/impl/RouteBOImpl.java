/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RouteDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.entity.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class RouteBOImpl implements RouteBO {

    private RouteDAO routeDAO;
    private SessionFactory sessionFactory;

    public RouteBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        this.routeDAO = (RouteDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROUTE);
    }
    @Override
    public RouteDTO findRoute(int routeid)throws Exception{
        Route routeResult = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            routeDAO.setSession(session);
            routeResult = routeDAO.findById(routeid);
            session.getTransaction().commit();
        }
        if(routeResult == null){
            return null;
        }
        return new RouteDTO(routeResult.getRouteid(), routeResult.getRoutename());
    }

    @Override
    public ArrayList<RouteDTO> getRoutes() throws Exception {
        ArrayList<RouteDTO> routeDTOs = new ArrayList<>();
        List<Route> routes = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            routeDAO.setSession(session);
            routes = routeDAO.getAll();
            session.getTransaction().commit();
        }
        if(routes == null){
            return null;
        }
        for (Route route : routes) {
            routeDTOs.add(new RouteDTO(route.getRouteid(), route.getRoutename()));
        }
        return routeDTOs;
    }

    @Override
    public RouteDTO getRouteByName(String name) throws Exception {
        Route routeID = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            routeDAO.setSession(session);
            routeID = routeDAO.getRouteID(name);
            session.getTransaction().commit();
        }
        return new RouteDTO(routeID.getRouteid(), name);
    }

    @Override
    public boolean saveRoute(RouteDTO routeDTO) throws Exception {
        Route route = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            routeDAO.setSession(session);
            route = routeDAO.saveAndGetGenerated(new Route( routeDTO.getRoute()));
            session.getTransaction().commit();
        }
        return route != null;
    }

    @Override
    public boolean updateRoute(RouteDTO routeDTO) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            routeDAO.setSession(session);
            Route route = routeDAO.findById(routeDTO.getRouteid());
            route.setRoutename(routeDTO.getRoute());
            routeDAO.update(route);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e1){
            throw e1;
        }

    }

    @Override
    public boolean deleteRoute(int routeid) throws Exception {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            routeDAO.setSession(session);
            routeDAO.delete(routeid);
            session.getTransaction().commit();
            return true;
        }
    }

    

    

    
}
