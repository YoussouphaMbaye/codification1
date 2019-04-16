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
public class Departement {
    int idUfr,idDepartement;
    String nomDepartement;

    public Departement() {
    }
    public Departement(int idUfr,int idDepartement,String nomDepartement) {
        this.idDepartement=idDepartement;
        this.idUfr=idUfr;
        this.nomDepartement=nomDepartement;
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

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }
    
    
    
}
