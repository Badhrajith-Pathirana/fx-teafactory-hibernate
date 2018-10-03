/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.PurchaseDAO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.*;
import lk.beempz.tf.entity.Purchase;
import lk.beempz.tf.entity.Route;
import lk.beempz.tf.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PurchaseBOImpl implements PurchaseBO {

    private PurchaseDAO purchaseDAO;
    private SupplierBO supplierBO;
    private DebitBO debitBO;
    private SessionFactory sessionFactory;
    

    public PurchaseBOImpl() {
        this.debitBO = (DebitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEBIT);
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        this.purchaseDAO = (PurchaseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addPurchase(PurchaseDTO purchaseDTO) throws Exception {
        MonthlyRateBO monthlyRateBO = (MonthlyRateBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MONTHLY_RATE);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            purchaseDAO.setSession(session);
//            DBConnection.getInstance().getConnection().setAutoCommit(false);
            MonthlyRateDTO rates = monthlyRateBO.getRates(new UnprocessedDebitDTO(purchaseDTO.getDate(), purchaseDTO.getSupplierid(), null, purchaseDTO.getaKg(), purchaseDTO.getbKg()));
            BigDecimal payforA = rates.getaGrade().multiply(purchaseDTO.getaKg());
            BigDecimal payforB = rates.getbGrade().multiply(purchaseDTO.getbKg());
            BigDecimal totalSize = purchaseDTO.getaKg().add(purchaseDTO.getbKg());
            BigDecimal payforTravel = rates.getTravelling().multiply(totalSize);
            BigDecimal totalAmount = payforA.add(payforB.subtract(payforTravel));
            SupplierDTO supplierDTO = supplierBO.findSupplier(purchaseDTO.getSupplierid());
            Supplier supplier = new Supplier(supplierDTO.getSupplierid(), supplierDTO.getName(), new Route(supplierDTO.getRouteid(), supplierDTO.getRoute()), supplierDTO.getContact(), supplierDTO.getAddress());
            Purchase purchase = purchaseDAO.saveAndGetGenerated(new Purchase(purchaseDTO.getDate(), supplier, purchaseDTO.getaKg(), purchaseDTO.getbKg()));
            if (purchase == null) {
                session.getTransaction().rollback();
                return false;
            }
            boolean result = debitBO.insertDebit(new DebitDTO(-1, purchaseDTO.getDate(), purchase.getPurchase_id(), purchaseDTO.getSupplierid(), purchaseDTO.getSuppliername(), totalAmount));
            if (result) {
//                DBConnection.getInstance().getConnection().commit();
                session.getTransaction().commit();
                return true;
            } else {
//                DBConnection.getInstance().getConnection().rollback();
                session.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
//            DBConnection.getInstance().getConnection().rollback();
            throw e;
        }
    }

    @Override
    public boolean deletePurchase(int pid, Date date) throws Exception {

        try (Session session = sessionFactory.openSession()) {
//            DBConnection.getInstance().getConnection().setAutoCommit(false);
            session.beginTransaction();
            purchaseDAO.setSession(session);
            Purchase result = purchaseDAO.findById(pid);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(result.getPurchase_date());
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            long diff = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (60000 * 60 * 24);
            if (diff > 1) {
                session.getTransaction().rollback();
                return false;
            }
            
            boolean res = debitBO.deleteByPurchase(pid);
            if(!res){
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
            purchaseDAO.delete(pid);
            if(!res){
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
            DBConnection.getInstance().getConnection().commit();
            return true;
                
        } catch (Exception e) {
//            DBConnection.getInstance().getConnection().rollback();
            throw e;
        }
    }

    @Override
    public ArrayList<PurchaseDTO> getAll() throws Exception {
        ArrayList<PurchaseDTO> purchaseDTOs = new ArrayList<>();
        List<Purchase> all = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            purchaseDAO.setSession(session);
            all = purchaseDAO.getAll();
            session.getTransaction().commit();
        }
        for (Purchase purchase : all) {
            purchaseDTOs.add(new PurchaseDTO(purchase.getPurchase_id(), purchase.getPurchase_date(), purchase.getSupplier().getSupplierno(), supplierBO.findSupplier(purchase.getSupplier().getSupplierno()).getName(), purchase.getAkg(), purchase.getBkg()));
        }
        return purchaseDTOs;
    }

    @Override
    public ArrayList<PurchaseDTO> getAllByMonth(Date date) throws Exception {
        ArrayList<PurchaseDTO> purchaseDTOs = new ArrayList<>();
        List<Purchase> purchases = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            purchaseDAO.setSession(session);
            purchases = purchaseDAO.getAllByMonth(date);
            session.getTransaction().commit();
        }
        for (Purchase purchase : purchases) {
            purchaseDTOs.add(new PurchaseDTO(purchase.getPurchase_id(), purchase.getPurchase_date(), purchase.getSupplier().getSupplierno(), supplierBO.findSupplier(purchase.getSupplier().getSupplierno()).getName(), purchase.getAkg(), purchase.getBkg()));
        }
        return purchaseDTOs;
    }

   

    

    

}
