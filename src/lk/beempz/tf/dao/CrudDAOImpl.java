package lk.beempz.tf.dao;

import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class CrudDAOImpl<T,ID> implements CrudDAO<T,ID>{

    protected Session session;

    private Class<T> entity;
    public CrudDAOImpl() {
        entity = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void save(T entity) {
        session.persist(entity);
    }

    @Override
    public void delete(ID id) {
        T t = session.find(entity, id);
        session.remove(t);
    }

    @Override
    public void update(T entity) {
        session.persist(entity);
    }

    @Override
    public List<T> getAll() {
        return session.createQuery("FROM "+entity.getName()).list();
    }

    @Override
    public T findById(ID id) {
        return session.find(entity,id);
    }

    @Override
    public T saveAndGetGenerated(T entity) {
        session.persist(entity);
//        session.refresh(entity);
        return entity;
    }


}
