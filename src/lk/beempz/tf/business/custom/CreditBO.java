/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.CreditDTO;

/**
 *
 * @author badhr
 */
public interface CreditBO extends SuperBO{
    public boolean insertCredit(CreditDTO creditDTO)throws Exception;
    public ArrayList<CreditDTO> getAllCredits(Date from , Date to);
    public boolean deleteCredit(CreditDTO creditDTO);
}
