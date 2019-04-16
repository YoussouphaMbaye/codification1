
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Formation;


public class FormationForm {
  String nomFormation;
  Formation formation=new Formation();
  List<Formation>listforms=new ArrayList<Formation>();

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<Formation> getListforms() {
        return listforms;
    }

    public void setListforms(List<Formation> listforms) {
        this.listforms = listforms;
    }
  
}
