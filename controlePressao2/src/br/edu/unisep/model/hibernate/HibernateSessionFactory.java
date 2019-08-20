package br.edu.unisep.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateSessionFactory {

    private static SessionFactory factory;

    static{
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }
    //Metodo responsavel por gerar uma nova seção com o banco de daddos

    public static Session getSession(){
        return factory.openSession();
    }


}
