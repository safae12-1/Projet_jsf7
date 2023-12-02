/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Safae
 */
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    public Service(String nom) {
        this.nom = nom;
    }

    public Service() {
    }
    

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
