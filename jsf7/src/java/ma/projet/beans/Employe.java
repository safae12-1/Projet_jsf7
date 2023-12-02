/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Safae
 */
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private  String url;
    private boolean isChef;

    @ManyToOne
    private Service service;
    
    @ManyToOne
    private Employe employe;
    
    @OneToMany(mappedBy = "employe")
    private List<Employe> employes;
    
    
    public Employe() {
        service = new Service();
    }

    public Employe(String nom, String prenom, Date dateNaissance, String url, Service service, Employe employe, List<Employe> employes, boolean isChief) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.url = url;
        this.service = service;
        this.employe = employe;
        this.employes = employes;
    }

    

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getUrl() {
        return url;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Service getService() {
        return service;
    }

    public Employe getEmploye() {
        return employe;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public boolean isIsChef() {
        return isChef;
    }

    public void setIsChef(boolean isChef) {
        this.isChef = isChef;
    }
  
            
            
    
}
