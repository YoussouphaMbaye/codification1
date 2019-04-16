/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Etudiant;

/**
 *
 * @author Side Sarr
 */
public class EtudiantForm {
    private int idEtudiant;
    private int numeroEtudiant;
    private Etudiant etu=new Etudiant();
    private List<Etudiant> listeEtudiant=new ArrayList<Etudiant>();

   public int getIdEtudiant() {
        return idEtudiant;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Etudiant getEtu() {
        return etu;
    }

    public void setEtu(Etudiant etu) {
        this.etu = etu;
    }

    public List<Etudiant> getListeEtudiant() {
        return listeEtudiant;
    }

    public void setListeEtudiant(List<Etudiant> listeEtudiant) {
        this.listeEtudiant = listeEtudiant;
    }
    
}
