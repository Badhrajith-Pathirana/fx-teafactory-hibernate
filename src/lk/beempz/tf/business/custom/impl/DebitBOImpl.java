/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateBO;
import lk.beempz.tf.business.custom.SupplierBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.DebitDAO;
import lk.beempz.tf.dao.custom.PurchaseDAO;
import lk.beempz.tf.dao.custom.SupplierDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.SupplierDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;
import lk.beempz.tf.entity.Debit;
import lk.beempz.tf.entity.Purchase;
import lk.beempz.tf.entity.Route;
import lk.beempz.tf.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class DebitBOImpl implements DebitBO {

    private DebitDAO debitDAO;
    private SupplierBO supplierBO;
    private MonthlyRateBO monthlyRateBO;
    private SessionFactory sessionFactory;
    private PurchaseDAO purchaseDAO;
    private SupplierDAO supplierDAO;

    public DebitBOImpl() {
        this.monthlyRateBO = (MonthlyRateBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MONTHLY_RATE);
        this.supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        this.debitDAO = (DebitDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DEBIT);
        sessionFactory = HibernateUtil.getSessionFactory();
        purchaseDAO = (PurchaseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);
        this.supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    }
    @Override
    public ArrayList<DebitDTO> getDebitList(Date from, Date to) throws Exception {
        ArrayList<DebitDTO> debitDTOs = new ArrayList<>();
        List<Debit> debits = null;

        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            if(from == null && to == null){
                debits = debitDAO.getAll();
            }else{
//                debits = debitDAO.getSortAndFiltered(from, to);
            }
        }

        for (Debit debit : debits) {
            debitDTOs.add(new DebitDTO(debit.getDebitid(), debit.getDebitdate(), debit.getPurchase().getPurchase_id(), debit.getSupplier().getSupplierno(), supplierBO.findSupplier(debit.getSupplier().getSupplierno()).getName(), debit.getAmount()));
        }
        return debitDTOs;
    }

    

    @Override
    public boolean updateDebit(DebitDTO debitDTO){
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            purchaseDAO.setSession(session);
            Purchase purchase = purchaseDAO.findById(debitDTO.getPurchaseid());
            supplierDAO.setSession(session);
            Supplier supplier = supplierDAO.findById(debitDTO.getSupplierid());
            debitDAO.setSession(session);
            Debit debit = debitDAO.findById(debitDTO.getDebitid());
            debit.setDebitdate(debitDTO.getDate());
            debit.setPurchase(purchase);
            debit.setAmount(debitDTO.getAmount());
            debit.setSupplier(supplier);
            debitDAO.update(debit);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e1){
            return false;
        }
    }

    @Override
    public boolean deleteDebit(int debitId){
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            debitDAO.delete(debitId);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e1){
            return false;
        }
    }

    @Override
    public boolean insertDebit(DebitDTO debitDTO)throws Exception{
        Debit debit = null;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            SupplierDTO supplierDTO = supplierBO.findSupplier(debitDTO.getSupplierid());
            Supplier supplier = new Supplier(supplierDTO.getSupplierid(), supplierDTO.getName(), new Route(supplierDTO.getRouteid(), supplierDTO.getRoute()), supplierDTO.getContact(), supplierDTO.getAddress());
            purchaseDAO.setSession(session);
            Purchase purchase = purchaseDAO.findById(debitDTO.getPurchaseid());
            debitDAO.setSession(session);
            debit = debitDAO.saveAndGetGenerated(new Debit( debitDTO.getDate(), debitDTO.getAmount(),purchase, supplier));
            session.getTransaction().commit();
        }
        return debit != null;
    }

    @Override
    public boolean deleteByPurchase(int purchaseid) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            debitDAO.setSession(session);
            debitDAO.deleteByPurchaseid(purchaseid);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e1){
            return false;
        }
    }

    @Override
    public DebitDTO getAllByPurchaseID(int purchaseid) throws Exception{
        Debit debit = null;

        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            debitDAO.setSession(session);
            debit = debitDAO.selectByPurchaseID(purchaseid);
            session.getTransaction().commit();
        }
        if(debit == null)
            return null;
        return new DebitDTO(debit.getDebitid(), debit.getDebitdate(), debit.getPurchase().getPurchase_id(), debit.getSupplier().getSupplierno(), supplierBO.findSupplier(debit.getSupplier().getSupplierno()).getName(), debit.getAmount());
    }

    

    
    
}
