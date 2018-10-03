/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom;

import java.util.ArrayList;
import lk.beempz.tf.business.SuperBO;
import lk.beempz.tf.dto.SupplierDTO;

/**
 *
 * @author badhr
 */
public interface SupplierBO extends SuperBO{
    public ArrayList<SupplierDTO> getSuppliers()throws Exception;
    public SupplierDTO findSupplier(int Id)throws Exception;
    public boolean addNewSupplier(SupplierDTO supplier)throws Exception;
    public boolean updateSupplier(SupplierDTO supplier)throws Exception;
    public boolean deleteSupplier(int id)throws Exception;
    public int addAndReturnGenerated(SupplierDTO supplier)throws Exception;
}
