/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Employer;

/**
 *
 * @author Side Sarr
 */
public class EmployerForm {

    private String matricule;
    private String profile;
    Employer emp = new Employer();

    List<Employer> listeEmployer = new ArrayList<Employer>();

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Employer getEmp() {
        return emp;
    }

    public void setEmp(Employer emp) {
        this.emp = emp;
    }

    public List<Employer> getListeEmployer() {
        return listeEmployer;
    }

    public void setListeEmployer(List<Employer> listeEmployer) {
        this.listeEmployer = listeEmployer;
    }

}
