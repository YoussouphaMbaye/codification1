
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Etudie;


public class EtudieForm {
   private int idEtudiant;
   private int idClasse;
   private int annee;
   private Etudie etudie=new Etudie();
   private List<Etudie>listeEtudie=new ArrayList<Etudie>();

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Etudie getEtudie() {
        return etudie;
    }

    public void setEtudie(Etudie etudie) {
        this.etudie = etudie;
    }

    public List<Etudie> getListeEtudie() {
        return listeEtudie;
    }

    public void setListeEtudie(List<Etudie> listeEtudie) {
        this.listeEtudie = listeEtudie;
    }
   
}
