/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import metier.Choisir;

/**
 *
 * @author Side Sarr
 */
public class ChoisirForm {
    private int id_pavillon;
    private int id_chambre;
    private int id_etudiant;
    private String erreur;
    private Choisir c=new Choisir();
    List<Choisir> listeChoisir=new ArrayList<Choisir>();

    public int getId_pavillon() {
        return id_pavillon;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
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

   

    public Choisir getC() {
        return c;
    }

    public void setC(Choisir c) {
        this.c = c;
    }

    public List<Choisir> getListeChoisir() {
        return listeChoisir;
    }

    public void setListeChoisir(List<Choisir> listeChoisir) {
        this.listeChoisir = listeChoisir;
    }
    
    
}
