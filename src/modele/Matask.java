
package modele;

import java.sql.Date;
import java.time.Period;
import java.util.TimerTask;
import metier.Choisir;
import metier.GestChoisir;


public class Matask extends TimerTask {

    @Override
    public void run() {
        GestChoisir gstCh=new GestChoisir();
        for(Choisir ch: gstCh.listerChoix() ){
         Date dateDuChoix=new Date(ch.getDate().getTime()+24*60*60*3*1000);
         
         java.util.Date today = new java.util.Date();
        Date dateActuel= new java.sql.Date(today.getTime());
       if(dateActuel.after(dateDuChoix)){
           gstCh.supprimerChoix(ch);
       }
        }
             
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    
}
}
