/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao.custom;

import lk.beempz.tf.dao.CrudDAO;
import lk.beempz.tf.entity.Credit_Type;

/**
 *
 * @author badhr
 */
public interface Credit_TypeDAO extends CrudDAO<Credit_Type, Integer>{
    public int getCreditTypeid(String creditType);
}
