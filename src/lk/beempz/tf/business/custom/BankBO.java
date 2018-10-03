/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.BankDTO;

/**
 *
 * @author badhr
 */
public interface BankBO extends SuperBO{
    public BankDTO saveBank(BankDTO bank);
    public boolean updateBank(BankDTO bank);
    public boolean deleteBank(int bankid);
    public ArrayList<BankDTO> getAllBanks();
    public int getBankID(String bankName);
    public String findBankName(int bankid);
    public BankDTO findBank(int bankID);
}
