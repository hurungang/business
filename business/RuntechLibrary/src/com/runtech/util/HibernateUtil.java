package com.runtech.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static final SessionFactory SessionFactory;

    static 
    {
        try 
        {
            // Create the SessionFactory from hibernate.cfg.xml
            SessionFactory = new Configuration().configure().buildSessionFactory();
        } 
        catch (Throwable ex) 
        {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static final ThreadLocal session = new ThreadLocal();

    /**
     * return current session
     * @return current session
     * @throws HibernateException
     */
    public static Session getCurrentSession() throws HibernateException 
    {
        Session s = (Session) session.get();
        // Open a new Session, if this thread has none yet
        if (s == null|| !s.isOpen()) 
        {
            s = SessionFactory.openSession();
        	s.flush();
        	s.clear();
            // Store it in the ThreadLocal variable
            session.set(s);
        }
        if(!s.isConnected())
        	s.reconnect();
        return s;
    }

    /**
     * close current session
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException 
    {
        Session s = (Session) session.get();
        if (s != null)
        {
        	s.close();
        }
        session.set(null);
    }
}
