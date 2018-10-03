/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.Date;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.MonthlyRateDTO;

/**
 *
 * @author badhr
 */
public interface MonthlyRateNewBO extends SuperBO{
    public boolean insertMonthlyRates(MonthlyRateDTO debitDTO) ;
    public MonthlyRateDTO findByID(Date date);
}
