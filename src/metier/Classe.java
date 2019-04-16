/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;

/**
 *
 * @author Side Sarr
 */
public class Classe implements Serializable{
    private int id_classe;
    private String formation;
    private String departement;
    private String ufr;
    private String niveau;

    public Classe( String formation, String departement, String ufr, String niveau) {
        this.formation = formation;
        this.departement = departement;
        this.ufr = ufr;
        this.niveau = niveau;
    }
    public Classe() {
       
    }

    public Classe(int id_classe, String formation, String departement, String ufr, String niveau) {
        this.id_classe = id_classe;
        this.formation = formation;
        this.departement = departement;
        this.ufr = ufr;
        this.niveau = niveau;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getUfr() {
        return ufr;
    }

    public void setUfr(String ufr) {
        this.ufr = ufr;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    
    
}
