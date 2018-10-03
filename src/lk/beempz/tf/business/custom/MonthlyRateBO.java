/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.DebitDTO;
import lk.beempz.tf.dto.MonthlyRateDTO;
import lk.beempz.tf.dto.UnprocessedDebitDTO;

/**
 *
 * @author badhr
 */
public interface MonthlyRateBO extends SuperBO{
    //public boolean insertMonthlyRates(MonthlyRateDTO debitDTO)throws Exception;
    public MonthlyRateDTO getRates(UnprocessedDebitDTO debitDTO);
    public boolean updateMonthlyRates(MonthlyRateDTO monthlyRateDTO);
    public ArrayList<MonthlyRateDTO> getAllRates();
    
}
