/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dto;

/**
 *
 * @author badhr
 */
public class CreditTypeDTO {
    private int creditTypeid;
    private String creditType;

    public CreditTypeDTO() {
    }

    public CreditTypeDTO(int creditTypeid, String creditType) {
        this.creditTypeid = creditTypeid;
        this.creditType = creditType;
    }

    /**
     * @return the creditTypeid
     */
    public int getCreditTypeid() {
        return creditTypeid;
    }

    /**
     * @param creditTypeid the creditTypeid to set
     */
    public void setCreditTypeid(int creditTypeid) {
        this.creditTypeid = creditTypeid;
    }

    /**
     * @return the creditType
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * @param creditType the creditType to set
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }
    
}
