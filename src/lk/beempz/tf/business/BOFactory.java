/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business;

import lk.beempz.tf.business.custom.MonthlyRateNewBO;
import lk.beempz.tf.business.custom.impl.BankBOImpl;
import lk.beempz.tf.business.custom.impl.BranchBOImpl;
import lk.beempz.tf.business.custom.impl.CreditBOImpl;
import lk.beempz.tf.business.custom.impl.CreditTypeBOImpl;
import lk.beempz.tf.business.custom.impl.DebitBOImpl;
import lk.beempz.tf.business.custom.impl.MonthlyRateBOImpl;
import lk.beempz.tf.business.custom.impl.MonthlyRateNewBOImpl;
import lk.beempz.tf.business.custom.impl.PurchaseBOImpl;
import lk.beempz.tf.business.custom.impl.RouteBOImpl;
import lk.beempz.tf.business.custom.impl.SupplierBOImpl;
import lk.beempz.tf.business.custom.impl.Supplier_BankBOImpl;
import lk.beempz.tf.business.custom.impl.UserBoImpl;

/**
 *
 * @author badhr
 */
public class BOFactory {

    private static BOFactory bOFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (bOFactory == null) {
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }

    public enum BOTypes {
        USER,ROUTE,SUPPLIER,BANK,BRANCH,DEBIT,MONTHLY_RATE,CREDIT_TYPE,CREDIT,PURCHASE,MONTHLY_RATE_NEW,SUPPLIER_BANK;
    }

    public SuperBO getBO(BOTypes bOTypes) {
        switch (bOTypes) {
            case USER:
                return new UserBoImpl();
            case ROUTE:
                return new RouteBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case BANK:
                return new BankBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case DEBIT:
                return new DebitBOImpl();
            case MONTHLY_RATE:
                return new MonthlyRateBOImpl();
            case CREDIT_TYPE:
                return new CreditTypeBOImpl();
            case CREDIT:
                return new CreditBOImpl();
            case PURCHASE:
                return new PurchaseBOImpl();
            case MONTHLY_RATE_NEW:
                return new MonthlyRateNewBOImpl();
            case SUPPLIER_BANK:
                return new Supplier_BankBOImpl();
            default:
                return null;
        }
    }
}
