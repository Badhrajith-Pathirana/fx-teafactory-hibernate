/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.PurchaseDTO;

/**
 *
 * @author badhr
 */
public interface PurchaseBO extends SuperBO{
    public boolean addPurchase(PurchaseDTO purchaseDTO)throws Exception;
    public boolean deletePurchase(int pid,Date date)throws Exception;
    public ArrayList<PurchaseDTO> getAll()throws Exception;
    public ArrayList<PurchaseDTO> getAllByMonth(Date date)throws Exception;
    //public boolean updatePurchase(PurchaseDTO purchaseDTO)throws Exception;
   // public DebitDTO getDebitByPurchaseID(int purchaseid)throws Exception;
}
