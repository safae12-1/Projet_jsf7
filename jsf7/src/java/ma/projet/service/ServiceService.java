/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Safae
 */
public class ServiceService implements IDao<Service> {
    @Override
    public boolean create(Service s) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean update(Service s) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(s);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean delete(Service s) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(s);
        session.getTransaction().commit();
        return true;
    }

    public Service getById(long id) {
        Service service  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service  = (Service) session.get(Service.class, id);
        session.getTransaction().commit();
        return service;
    }

    @Override
    public List<Service> getAll() {
        
         List <Service> services = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services  = session.createQuery("from Service").list();
        session.getTransaction().commit();
        return services;
    }
    
}
