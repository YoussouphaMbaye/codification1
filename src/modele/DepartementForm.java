/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Departement;

/**
 *
 * @author Side Sarr
 */
public class DepartementForm {
    private int idDepartement;
    private Departement dept=new Departement();
    private List<Departement> listeDept=new ArrayList<Departement>();

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Departement getDept() {
        return dept;
    }

    public void setDept(Departement dept) {
        this.dept = dept;
    }

    public List<Departement> getListeDept() {
        return listeDept;
    }

    public void setListeDept(List<Departement> listeDept) {
        this.listeDept = listeDept;
    }
    
    
    
}
