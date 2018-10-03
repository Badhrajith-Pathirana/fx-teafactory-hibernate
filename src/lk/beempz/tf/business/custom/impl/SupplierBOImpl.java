/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.RouteBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RouteDAO;
import lk.beempz.tf.dao.custom.SupplierDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.RouteDTO;
import lk.beempz.tf.dto.SupplierDTO;
import lk.beempz.tf.entity.Route;
import lk.beempz.tf.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class SupplierBOImpl implements SupplierBO {

    private SupplierDAO supplierDAO;
    private RouteBO routeBO;
    private SessionFactory sessionFactory;
    private RouteDAO routeDAO;

    public SupplierBOImpl() {
        this.routeBO = (RouteBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROUTE);
        this.supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
        sessionFactory = HibernateUtil.getSessionFactory();
        this.routeDAO = (RouteDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROUTE);
    }
    @Override
    public ArrayList<SupplierDTO> getSuppliers() throws Exception {
        List<Supplier> suppliers = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            supplierDAO.setSession(session);
            suppliers = supplierDAO.getAll();
            session.getTransaction().commit();
        }
        ArrayList<SupplierDTO> supplierDTOs = new ArrayList<>();
        for (Supplier supplier : suppliers) {
//            RouteDTO route = routeBO.findRoute(supplier.getRoute());
            supplierDTOs.add(new SupplierDTO(supplier.getSupplierno(), supplier.getName(), supplier.getRoute().getRouteid(), supplier.getRoute().getRoutename(), supplier.getPhone(), supplier.getAddress()));
            
        }
        return supplierDTOs;
    }

    @Override
    public SupplierDTO findSupplier(int Id) throws Exception {
        Supplier supplier = null;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            supplierDAO.setSession(session);
            supplier = supplierDAO.findById(Id);
            session.getTransaction().commit();
        }
        if(supplier == null){
            return null;
        }
        return new SupplierDTO(supplier.getSupplierno(), supplier.getName(), supplier.getRoute().getRouteid(), supplier.getRoute().getRoutename(), supplier.getPhone(), supplier.getAddress());
        
    }

    @Override
    public boolean addNewSupplier(SupplierDTO supplier) throws Exception {
        if (supplier.getRouteid() == -1) {
            supplier.setRouteid(routeBO.getRouteByName(supplier.getRoute()).getRouteid());
        }
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            supplierDAO.setSession(session);
            if (supplier.getSupplierid() == -1) {
                Supplier id = supplierDAO.saveAndGetGenerated(new Supplier(supplier.getName(),   supplier.getContact(), supplier.getAddress(),new Route(supplier.getRouteid(),supplier.getRoute())));
                if (id == null) {
                    session.getTransaction().rollback();
                    return false;
                }
                session.getTransaction().commit();
                return true;
            }
            supplierDAO.save(new Supplier(supplier.getSupplierid(), supplier.getName(), new Route(supplier.getRouteid(), supplier.getRoute()), supplier.getContact(), supplier.getAddress()));
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        if(supplier.getRouteid() == -1){
            supplier.setRouteid(routeBO.getRouteByName(supplier.getRoute()).getRouteid());
        }
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            supplierDAO.setSession(session);
            Supplier byId = supplierDAO.findById(supplier.getSupplierid());
            routeDAO.setSession(session);
            Route route = routeDAO.findById(supplier.getRouteid());
            byId.setRoute(route);
            byId.setName(supplier.getName());
            byId.setAddress(supplier.getAddress());
            byId.setPhone(supplier.getContact());
            supplierDAO.update(byId);
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean deleteSupplier(int id)throws Exception{
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            supplierDAO.setSession(session);
            supplierDAO.delete(id);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public int addAndReturnGenerated(SupplierDTO supplier) throws Exception{
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            supplierDAO.setSession(session);
            if (supplier.getRouteid() == -1) {
                supplier.setRouteid(routeBO.getRouteByName(supplier.getRoute()).getRouteid());
            }
            if (supplier.getSupplierid() == -1) {
                Supplier id = supplierDAO.saveAndGetGenerated(new Supplier(-1, supplier.getName(), new Route(supplier.getRouteid(), supplier.getRoute()), supplier.getContact(), supplier.getAddress()));
                if (id == null) {
                    session.getTransaction().rollback();
                    return -1;
                }
                session.getTransaction().commit();
                return id.getSupplierno();
            }
            session.getTransaction().rollback();
            return -1;
        }
    }
}
