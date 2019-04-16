/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

/**
 *
 * @author Side Sarr
 */
public class Faire {
    int idEtudiant,idUfr,idDepartement,idFormation,annee;

    public Faire() {
    }
    public Faire(int idEtudiant,int idDepartement,int idUFr,int idFormation,int annee) {
        this.idEtudiant=idEtudiant;
        this.idDepartement=idDepartement;
        this.idUfr=idUFr;
        this.idFormation=idFormation;
        this.annee=annee;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdUfr() {
        return idUfr;
    }

    public void setIdUfr(int idUfr) {
        this.idUfr = idUfr;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    
}
