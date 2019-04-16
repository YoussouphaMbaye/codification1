
package metier;

import java.io.Serializable;


public class Etudie implements Serializable{
    private int idEtudiant;
    private int idClasse;
    private int annee;
public Etudie() {
        
    }
    public Etudie(int idEtudiant, int idClasse, int annee) {
        this.idEtudiant = idEtudiant;
        this.idClasse = idClasse;
        this.annee = annee;
    }

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
    
}
