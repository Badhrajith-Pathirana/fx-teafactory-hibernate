package lk.beempz.tf.db;

import lk.beempz.tf.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        File propFile = new File("resources/dbproperties.properties");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties(propFile).build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Bank.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(Credit.class)
                .addAnnotatedClass(Credit_Type.class)
                .addAnnotatedClass(Debit.class)
                .addAnnotatedClass(Purchase.class)
                .addAnnotatedClass(Rate.class)
                .addAnnotatedClass(Route.class)
                .addAnnotatedClass(Supplier.class)
                .addAnnotatedClass(Supplier_Bank.class)
                .addAnnotatedClass(User.class)
                .buildMetadata();

        return metadata.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
