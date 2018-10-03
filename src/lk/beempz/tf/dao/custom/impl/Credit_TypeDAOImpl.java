/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import lk.beempz.tf.dao.CrudDAOImpl;
import lk.beempz.tf.dao.custom.Credit_TypeDAO;
import lk.beempz.tf.entity.Credit_Type;


public class Credit_TypeDAOImpl extends CrudDAOImpl<Credit_Type,Integer> implements Credit_TypeDAO {
    @Override
    public int getCreditTypeid(String creditType){
        Credit_Type credit_type = session.createQuery("FROM Credit_Type WHERE type_name = ?1", Credit_Type.class).setParameter(1, creditType).getSingleResult();
        return credit_type.getTypeid();
    }
}
