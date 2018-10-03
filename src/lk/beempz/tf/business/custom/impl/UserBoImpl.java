/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.beempz.tf.business.custom.impl;

import lk.beempz.tf.dao.DAOFactory;
import lk.beempz.tf.dao.custom.UserDAO;
import lk.beempz.tf.db.HibernateUtil;
import lk.beempz.tf.dto.UserDTO;
import lk.beempz.tf.entity.User;
import lk.beempz.tf.business.custom.UserBO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class UserBoImpl implements UserBO {

    private UserDAO userDAO;
    private SessionFactory sessionFactory;

    public UserBoImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        this.userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    }
    @Override
    public boolean loginSuccess(UserDTO user)throws Exception {
        User result = null;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            userDAO.setSession(session);
            result = userDAO.findById(user.getUsername());
            session.getTransaction().commit();
        }

        if(result.getPassword().equals(user.getPassword()))
            return true;
        return false;
    }
    
}
