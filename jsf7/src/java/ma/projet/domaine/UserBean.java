/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ma.projet.beans.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ma.projet.util.HibernateUtil;

/**
 *
 * @author Safae
 */
@ManagedBean
@SessionScoped
public class UserBean {

    User users = new User();

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String loginCheck() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        String hashedPassword = hashPassword(users.getPassword());

        List<User> list = session.createSQLQuery("select * from user where userName='" + users.getUserName() + "' and password='" + hashedPassword + "'").list();
        if (list.size() > 0) {
            return "/web/salle/index";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Utilisateur non trouvé", ""));
        }

        session.getTransaction().commit();
        session.close();
        return null;
    }

    public void registerUser() {
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();

            users.setUserName(users.getUserName());
            users.setFamilyName(users.getFamilyName());
            users.setPassword(hashPassword(users.getPassword()));

            session.save(users);

            session.getTransaction().commit();
            session.close();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscription avec succès", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Echec à l'inscription", ""));
        }

    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            return no.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
