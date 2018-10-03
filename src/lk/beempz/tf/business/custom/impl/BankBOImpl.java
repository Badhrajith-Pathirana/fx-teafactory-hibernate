/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.beempz.tf.business.custom.BankBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.BankDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.BankDTO;
import lk.beempz.tf.entity.Bank;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class BankBOImpl implements BankBO {
    private BankDAO bankDAO;
    private SessionFactory sessionFactory;

    public BankBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        this.bankDAO = (BankDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BANK);
    }

    @Override
    public BankDTO saveBank(BankDTO bank) {
        Bank bankent = null;
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            bankDAO.setSession(session);
            bankent =  bankDAO.saveAndGetGenerated(new Bank(bank.getBankName()));
            session.getTransaction().commit();
        }
        if(bankent == null)
            return null;
        return new BankDTO(bankent.getBankid(), bankent.getBankName());
    }

    @Override
    public boolean updateBank(BankDTO bank) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bankDAO.setSession(session);
            Bank byId = bankDAO.findById(bank.getBankid());
            byId.setBankname(bank.getBankName());
            bankDAO.update(byId);
            session.getTransaction().commit();
            return true;
        }

    }

    @Override
    public boolean deleteBank(int bankid){
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            bankDAO.setSession(session);
            bankDAO.delete(bankid);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e1){
            return  false;
        }
    }

    @Override
    public ArrayList<BankDTO> getAllBanks(){
        ArrayList<BankDTO> bankDTOs = new ArrayList<>();
        List<Bank> banks = null;
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            bankDAO.setSession(session);
            banks = bankDAO.getAll();
            session.getTransaction().commit();
        }
        if(banks == null){
            return null;
        }
        for (Bank bank : banks) {
            bankDTOs.add(new BankDTO(bank.getBankid(), bank.getBankName()));
        }
        return bankDTOs;
    }

    @Override
    public int getBankID(String bankName){
        int id = -1;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            bankDAO.setSession(session);
            id = bankDAO.getID(bankName);
            session.getTransaction().commit();
        }
        return id;
    }

    @Override
    public String findBankName(int bankid){
        String bankName = null;
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            bankDAO.setSession(session);
            bankName = bankDAO.findById(bankid).getBankName();
            session.getTransaction().commit();
        }
        return bankName;
    }

    @Override
    public BankDTO findBank(int bankID) {
        Bank bank = null;
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            bankDAO.setSession(session);
            bank = bankDAO.findById(bankID);
            session.getTransaction().commit();
        }
        if(bank == null){
            return null;
        }
        return new BankDTO(bank.getBankid(),bank.getBankName());
    }
}
