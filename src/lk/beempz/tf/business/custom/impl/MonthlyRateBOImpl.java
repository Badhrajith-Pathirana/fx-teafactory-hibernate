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
import lk.beempz.tf.business.custom.PurchaseBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.RateDAO;
import lk.beempz.tf.db.DBConnection;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.PurchaseDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;
import lk.beempz.tf.entity.Debit;
import lk.beempz.tf.entity.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class MonthlyRateBOImpl implements MonthlyRateBO {

    private RateDAO rateDAO;
    private SessionFactory sessionFactory;
   // DebitBO debitBO;
    
    public MonthlyRateBOImpl() {
        
        //this.debitBO = (DebitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DEBIT);
        this.rateDAO = (RateDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RATE);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    

    

    @Override
    public boolean updateMonthlyRates(MonthlyRateDTO monthlyRateDTO){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            rateDAO.setSession(session);
            Rate byId = rateDAO.findById(monthlyRateDTO.getDate());
            System.out.println(byId);
            byId.setAkgper(monthlyRateDTO.getaGrade());
            byId.setBkgper(monthlyRateDTO.getbGrade());
            byId.setTravelling(monthlyRateDTO.getTravelling());
            session.refresh(byId);
//            rateDAO.update(new Rate(monthlyRateDTO.getDate(), monthlyRateDTO.getaGrade(), monthlyRateDTO.getbGrade(), monthlyRateDTO.getTravelling()));
            session.getTransaction().commit();
            return true;
        }
        /*catch (Exception e1){
            return false;
        }*/
    }

    @Override
    public MonthlyRateDTO getRates(UnprocessedDebitDTO debitDTO){
        Rate rate = null;

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            rateDAO.setSession(session);
            rate = rateDAO.findById(debitDTO.getDate());
            session.getTransaction().commit();
        }
        return new MonthlyRateDTO(debitDTO.getDate(), rate.getAkgper(), rate.getBkgper(), rate.getTravelling());
    }

    @Override
    public ArrayList<MonthlyRateDTO> getAllRates(){
        ArrayList<MonthlyRateDTO> monthlyRateDTOs = new ArrayList<>();
        List<Rate> all = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            rateDAO.setSession(session);
            all = rateDAO.getAll();
            session.getTransaction().commit();
        }
        if(all == null)
            return null;
        for (Rate rate : all) {
            monthlyRateDTOs.add(new MonthlyRateDTO(rate.getRateMonth(), rate.getAkgper(), rate.getBkgper(), rate.getTravelling()));
        }
        return monthlyRateDTOs;
    }

    
}
