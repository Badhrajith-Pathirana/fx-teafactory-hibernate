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
import lk.beempz.tf.dao.custom.PurchaseDAO;
import lk.beempz.tf.entity.Purchase;


public class PurchaseDAOImpl extends CrudDAOImpl<Purchase,Integer> implements PurchaseDAO {

    @Override
    public List<Purchase> getAllByMonth(Date date){
        return session.createQuery("FROM Purchase WHERE Month(purchase_id) = MONTH(?1)",Purchase.class).setParameter(1,date).list();
    }
}
