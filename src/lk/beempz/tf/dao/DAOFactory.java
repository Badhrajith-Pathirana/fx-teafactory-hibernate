/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao;

import lk.beempz.tf.dao.custom.impl.BankDAOImpl;
import lk.beempz.tf.dao.custom.impl.BranchDAOImpl;
import lk.beempz.tf.dao.custom.impl.CreditDAOImpl;
import lk.beempz.tf.dao.custom.impl.Credit_TypeDAOImpl;
import lk.beempz.tf.dao.custom.impl.DebitDAOImpl;
import lk.beempz.tf.dao.custom.impl.PurchaseDAOImpl;
import lk.beempz.tf.dao.custom.impl.RateDAOImpl;
import lk.beempz.tf.dao.custom.impl.RouteDAOImpl;
import lk.beempz.tf.dao.custom.impl.SupplierDAOImpl;
import lk.beempz.tf.dao.custom.impl.Supplier_BankDAOImpl;
import lk.beempz.tf.dao.custom.impl.UserDAOImpl;

/**
 *
 * @author badhr
 */
public class DAOFactory {

    private static DAOFactory dAOFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (dAOFactory == null) {
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }

    public static enum DAOTypes {
        BANK, BRANCH, CREDIT, CREDIT_TYPE, DEBIT, PURCHASE, RATE, ROUTE, SUPPLIER, SUPPLIER_BANK, USER;
    }

    public SuperDAO getDAO(DAOTypes daotype) {
        switch (daotype) {
            case BANK:
                return new BankDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case CREDIT:
                return new CreditDAOImpl();
            case CREDIT_TYPE:
                return new Credit_TypeDAOImpl();
            case DEBIT:
                return new DebitDAOImpl();
            case PURCHASE:
                return new PurchaseDAOImpl();
            case RATE:
                return new RateDAOImpl();
            case ROUTE:
                return new RouteDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_BANK:
                return new Supplier_BankDAOImpl();
            case USER:
                return new UserDAOImpl();
            default :
                return null;
        }
    }
}
