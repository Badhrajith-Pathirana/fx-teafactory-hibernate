/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.DebitBO;
import lk.beempz.tf.business.custom.MonthlyRateNewBO;
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RateDAO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.entity.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class MonthlyRateNewBOImpl implements MonthlyRateNewBO {
    private DebitBO debitBO;
    private RateDAO rateDAO;
    private SessionFactory sessionFactory;

    public MonthlyRateNewBOImpl() {
        this.rateDAO = (RateDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RATE);
        this.debitBO = (DebitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEBIT);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean insertMonthlyRates(MonthlyRateDTO debitDTO){
        PurchaseBO purchaseBO = (PurchaseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE);
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            rateDAO.setSession(session);
//            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Rate byId = rateDAO.findById(debitDTO.getDate());
            System.out.println(byId);
            byId.setAkgper(debitDTO.getaGrade());
            byId.setBkgper(debitDTO.getbGrade());
            byId.setTravelling(debitDTO.getTravelling());
//            session.refresh(byId);
            rateDAO.update(byId);
//            ArrayList<PurchaseDTO> purchaseDTOs = purchaseBO.getAllByMonth(debitDTO.getDate());
//            for (PurchaseDTO purchaseDTO : purchaseDTOs) {
//                BigDecimal aPrice = purchaseDTO.getaKg().multiply(debitDTO.getaGrade());
//                BigDecimal bPrice = purchaseDTO.getbKg().multiply(debitDTO.getbGrade());
//                BigDecimal totalTea = purchaseDTO.getaKg().add(purchaseDTO.getbKg());
//                BigDecimal travellingPrice = debitDTO.getTravelling().multiply(totalTea);
//                BigDecimal totalPricee = aPrice.add(bPrice.subtract(travellingPrice));
//                DebitDTO purchase = debitBO.getAllByPurchaseID(purchaseDTO.getPurchaseid());
//                boolean res = debitBO.updateDebit(new DebitDTO(purchase.getDebitid(), purchaseDTO.getDate(), purchaseDTO.getPurchaseid(), purchaseDTO.getSupplierid(), null, totalPricee));
//                if(!res){
////                    DBConnection.getInstance().getConnection().rollback();
//                    session.getTransaction().rollback();
//                    return false;
//                }
//
//            }
            session.getTransaction().commit();
//            DBConnection.getInstance().getConnection().commit();
            return true;
            
        } /*catch (Exception e) {
//            DBConnection.getInstance().getConnection().rollback();
            return false;
        }*/
    }
    public MonthlyRateDTO findByID(Date date){
        Rate findById = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            rateDAO.setSession(session);
            findById = rateDAO.findById(date);
            session.getTransaction().commit();
        }
        if(findById == null)
            return null;
        return new MonthlyRateDTO(findById.getRateMonth(), findById.getAkgper(), findById.getBkgper(), findById.getTravelling());
    }
    
}
