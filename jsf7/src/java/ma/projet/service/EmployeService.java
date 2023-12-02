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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Safae
 */
public class EmployeService implements IDao<Employe> {
    
    private Session session;
    
    public EmployeService(){
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public boolean create(Employe e) {
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe e) {
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe e) {
        session.beginTransaction();
        session.delete(e);
        session.getTransaction().commit();
        return true;
    }

    public Employe getById(long id) {
        Employe employe = null;
        session.beginTransaction();
        employe = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return employe;
    }

    @Override
    public List<Employe> getAll() {

        List<Employe> employes = null;
        session.beginTransaction();
        employes = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return employes;
    }

    public List<Object[]> nbemploye() {
        List<Object[]> employes = null;
        session.beginTransaction();
        employes = session.createQuery("select count(m.nom), m.nom from Employe m group by m.nom").list();
        session.getTransaction().commit();
        return employes;
    }
    public List<Employe> getEmployeesByService(Service service){
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "FROM Employe e WHERE e.service = :service";
            Query query = session.createQuery(hql);
            query.setParameter("service", service);
            employes = query.list();
            return employes;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
            return employes;
        } finally {
            if(session != null)
                session.close();
        }
    }
    
    public List<Employe> getManagedEmployees(Employe employe){
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String hql = "FROM Employe e WHERE e.employe = :employe";
            Query query = session.createQuery(hql);
            query.setParameter("employe", employe);
            employes = query.list();
            return employes;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
            return employes;
        } finally {
            if(session != null)
                session.close();
        }
    }
  
 

}
