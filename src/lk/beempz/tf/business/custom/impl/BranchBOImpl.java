/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.beempz.tf.business.BOFactory;
import lk.beempz.tf.business.custom.BankBO;
import lk.beempz.tf.business.custom.BranchBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.BankDAO;
import lk.beempz.tf.dao.custom.BranchDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.BankDTO;
import lk.beempz.tf.dto.BranchDTO;
import lk.beempz.tf.entity.Bank;
import lk.beempz.tf.entity.Branch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class BranchBOImpl implements BranchBO {

    private BranchDAO branchDAO;
    private BankBO bankBO;
    private SessionFactory sessionFactory;
    private BankDAO bankDAO;

    public BranchBOImpl() {
        this.bankBO = (BankBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BANK);
        this.branchDAO = (BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRANCH);
        sessionFactory = HibernateUtil.getSessionFactory();
        this.bankDAO = (BankDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BANK);
    }
    @Override
    public boolean saveBranch(BranchDTO branch){
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            BankDTO bank = bankBO.findBank(bankBO.getBankID(branch.getBankName()));
            branchDAO.setSession(session);
            System.out.println(bank.getBankid());
            branchDAO.save(new Branch(new Bank(bank.getBankid(),bank.getBankName()),branch.getBranchName()));
            session.getTransaction().commit();
            return true;
        }


    }

    @Override
    public boolean updateBranch(BranchDTO branch){
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            branchDAO.setSession(session);
            bankDAO.setSession(session);
            int id = bankDAO.getID(branch.getBankName());
            Bank bank = bankDAO.findById(id);
            Branch branchEnt = branchDAO.findById(branch.getBranchid());
            branchEnt.setBranchName(branch.getBranchName());
            branchEnt.setBank(bank);
            branchDAO.update(branchEnt);
            session.getTransaction().commit();
            return true;
        }


    }

    @Override
    public ArrayList<BranchDTO> getBranches(){
        List<Branch> branches = null;
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            branchDAO.setSession(session);
            branches = branchDAO.getAll();
            session.getTransaction().commit();
        }
        ArrayList<BranchDTO> branchDTOs = new ArrayList<>();
        for (Branch branch : branches) {
            branchDTOs.add(new BranchDTO(branch.getBranchid(), branch.getBank().getBankid(), branch.getBranchName(),bankBO.findBankName(branch.getBank().getBankid())));
        }
        return branchDTOs;
    }

    @Override
    public int getBank(BranchDTO branch){
        if(branch.getBankid()==-1){
            return bankBO.getBankID(branch.getBankName());
        }
        return branch.getBankid();
    }

    @Override
    public boolean deleteBranch(int branchid){
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            branchDAO.setSession(session);
            branchDAO.delete(branchid);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e1){
            return false;
        }
    }

    
    
}
