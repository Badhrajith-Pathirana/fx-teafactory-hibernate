/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.Supplier_BankDTO;

/**
 *
 * @author beempz
 */
public interface Supplier_BankBO extends SuperBO{
    public boolean addSuplierBank(Supplier_BankDTO supplier_BankDTO);
}
