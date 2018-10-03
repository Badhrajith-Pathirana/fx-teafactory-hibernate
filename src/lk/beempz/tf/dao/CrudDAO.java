/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.dao;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author badhr
 */
public interface CrudDAO<T,ID> extends SuperDAO{
    public void setSession(Session session);
    public void save(T entity);
    public void delete(ID id);
    public void update(T entity);
    public List<T> getAll();
    public T findById(ID id);
    public T saveAndGetGenerated(T entity);
    
}
