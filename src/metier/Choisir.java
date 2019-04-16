/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author Side Sarr
 */
public class Choisir implements Serializable{
    private int id_pavillon;
    private int id_chambre;
    private int id_etudiant;
    private Date date;

    public Choisir(int id_pavillon, int id_chambre, int id_etudiant) {
        this.id_pavillon = id_pavillon;
        this.id_chambre = id_chambre;
        this.id_etudiant = id_etudiant;
        java.util.Date today = new java.util.Date();
        this.date = new java.sql.Date(today.getTime());
    }
     public Choisir(int id_pavillon, int id_chambre, int id_etudiant,Date date) {
        this.id_pavillon = id_pavillon;
        this.id_chambre = id_chambre;
        this.id_etudiant = id_etudiant;
        this.date = date;
    }
    public Choisir() {
       java.util.Date today = new java.util.Date();
        this.date = new java.sql.Date(today.getTime());  
    }

    public int getId_pavillon() {
        return id_pavillon;
    }

    public void setId_pavillon(int id_pavillon) {
        this.id_pavillon = id_pavillon;
    }

    public int getId_chambre() {
        return id_chambre;
    }

    public void setId_chambre(int id_chambre) {
        this.id_chambre = id_chambre;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public java.sql.Date getDate() {
         
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
