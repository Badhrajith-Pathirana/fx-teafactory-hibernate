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
import lk.beempz.tf.dto.UnprocessedDebitDTO;

/**
 *
 * @author badhr
 */
public interface DebitBO extends SuperBO{
    public ArrayList<DebitDTO> getDebitList(Date from , Date to)throws Exception;
    public boolean insertDebit(DebitDTO debitDTO)throws Exception;
    public boolean updateDebit(DebitDTO debitDTO);
    public boolean deleteDebit(int debitId);
    public boolean deleteByPurchase(int purchaseid);
   // public ArrayList<DebitDTO> getByMonth(Date date)throws Exception;
    public DebitDTO getAllByPurchaseID(int purchaseid)throws Exception;
    
}
