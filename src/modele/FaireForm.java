package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Faire;

public class FaireForm {

    Faire faire = new Faire();
    List<Faire> listeFaire = new ArrayList<Faire>();

    public Faire getFaire() {
        return faire;
    }

    public void setFaire(Faire faire) {
        this.faire = faire;
    }

    public List<Faire> getListeFaire() {
        return listeFaire;
    }

    public void setListeFaire(List<Faire> listeForm) {
        this.listeFaire = listeForm;
    }

}
