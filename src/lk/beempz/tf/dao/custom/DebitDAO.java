/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom;

import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.dao.CrudDAO;
import lk.beempz.tf.entity.Debit;

/**
 *
 * @author badhr
 */
public interface DebitDAO extends CrudDAO<Debit, Integer>{
    public void deleteByPurchaseid(int purchaseid);
    public Debit selectByPurchaseID(int purchaseid);
}
