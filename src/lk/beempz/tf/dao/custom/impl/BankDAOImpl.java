/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import lk.beempz.tf.dao.CrudDAOImpl;
import lk.beempz.tf.dao.custom.BankDAO;
import lk.beempz.tf.entity.Bank;
import org.hibernate.query.Query;

public class BankDAOImpl extends CrudDAOImpl<Bank,Integer> implements BankDAO  {
    @Override
    public int getID(String bankName){
        Bank bank = session.createQuery("SELECT b FROM Bank b WHERE bankName=?1", Bank.class).setParameter(1, bankName).getSingleResult();
        return bank.getBankid();
    }
}
