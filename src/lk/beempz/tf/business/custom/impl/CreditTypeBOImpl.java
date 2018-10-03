/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.beempz.tf.business.custom.CreditTypeBO;
import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.Credit_TypeDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.CreditTypeDTO;
import lk.beempz.tf.entity.Credit_Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class CreditTypeBOImpl implements CreditTypeBO {

    private Credit_TypeDAO credit_TypeDAO;
    private SessionFactory sessionFactory;

    public CreditTypeBOImpl() {
        this.credit_TypeDAO = (Credit_TypeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CREDIT_TYPE);
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    @Override
    public CreditTypeDTO getCreditType(int id){
        Credit_Type ctype = null;
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            credit_TypeDAO.setSession(session);
            ctype = credit_TypeDAO.findById(id);
            session.getTransaction().commit();
        }
        if(ctype == null)
            return null;
        return new CreditTypeDTO(ctype.getTypeid(), ctype.getType_name());
    }

    @Override
    public ArrayList<CreditTypeDTO> getCredits(){
        ArrayList<CreditTypeDTO> creditTypeDTOs = new ArrayList<>();
        List<Credit_Type> all = null;
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            credit_TypeDAO.setSession(session);
            all=credit_TypeDAO.getAll();
            session.getTransaction().commit();
        }
        for (Credit_Type credit_Type : all) {
            creditTypeDTOs.add(new CreditTypeDTO(credit_Type.getTypeid(), credit_Type.getType_name()));
        }
        return creditTypeDTOs;
    }

    @Override
    public int getIdByName(String type_Name) {
        int creditTypeid = -1;
        try (Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            credit_TypeDAO.setSession(session);
            creditTypeid = credit_TypeDAO.getCreditTypeid(type_Name);
            session.getTransaction().commit();
        }
        return creditTypeid;

    }
    
}
