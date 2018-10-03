/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.beempz.tf.dao.CrudDAOImpl;
import lk.beempz.tf.dao.custom.CreditDAO;
import lk.beempz.tf.entity.Credit;


public class CreditDAOImpl extends CrudDAOImpl<Credit,Integer> implements CreditDAO {
    @Override
    public List<Credit> getFromTo(Date from, Date to) {
        return session.createQuery("SELECT FROM Credit WHERE date between ?1 and ?2",Credit.class).setParameter(1,from).setParameter(2,to).list();
    }
}
