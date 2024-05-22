package com.learning.imst.ist.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class HibernateInsertThread implements Runnable{

    @Autowired
    SessionFactory sessionFactory;

    private Object object;

    public HibernateInsertThread(){
        System.out.println("This is default constructor");
    }

    public void setObject(Object object){
        this.object = object;
    }

    public void run(){
        Transaction transaction = null;
        Session session = null;
        try {
            System.out.println("Thread execution");
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(this.object);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
