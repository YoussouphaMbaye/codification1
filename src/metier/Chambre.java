/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;


/**
 *
 * @author Side Sarr
 */
public class Chambre implements Serializable{
    private int id_pavillon;
    private int id_chambre;
    private boolean reserver;

    public Chambre(int id_pavillon, int id_chambre,boolean  reserver ) {
        this.id_pavillon = id_pavillon;
        this.id_chambre = id_chambre;
        this.reserver=reserver;
    }
     public Chambre() {

    }

    public int getId_pavillon() {
        return id_pavillon;
    }

    public void setId_pavillon(int id_pavillon) {
        this.id_pavillon = id_pavillon;
    }

    public int getId_chambre() {
        return id_chambre;
    }

    public void setId_chambre(int id_chambre) {
        this.id_chambre = id_chambre;
    }

    public boolean isReserver() {
        return reserver;
    }

    public void setReserver(boolean reserver) {
        this.reserver = reserver;
    }
    
    
}
