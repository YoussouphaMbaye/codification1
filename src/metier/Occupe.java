package metier;

import java.io.Serializable;
import java.sql.Date;

public class Occupe implements Serializable {

    private int idPavion;
    private int idChambre;
    private int idEtudiant;
    private int annee;
    private Date dateOccupe;

    public Occupe() {
        java.util.Date today = new java.util.Date();
        this.dateOccupe = new java.sql.Date(today.getTime());
    }

    public Date getDateOccupe() {
        return dateOccupe;
    }

    public void setDatePloyer(Date dateOccupe) {
        this.dateOccupe = dateOccupe;
    }

    public Occupe(int idPavion, int idChambre, int idEtudiant, int annee) {
        this.idPavion = idPavion;
        this.idChambre = idChambre;
        this.idEtudiant = idEtudiant;
        this.annee = annee;
        java.util.Date today = new java.util.Date();
        this.dateOccupe = new java.sql.Date(today.getTime());
    }

    public int getIdPavion() {
        return idPavion;
    }

    public void setIdPavion(int idPavion) {
        this.idPavion = idPavion;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

}
