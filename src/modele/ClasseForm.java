/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Classe;

/**
 *
 * @author Side Sarr
 */
public class ClasseForm {
     private int id_classe;
     private Classe classe=new Classe();
     List<Classe> listeClasse=new ArrayList<>();

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getListeClasse() {
        return listeClasse;
    }

    public void setListeClasse(List<Classe> listeClasse) {
        this.listeClasse = listeClasse;
    }
     
}
