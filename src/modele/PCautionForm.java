
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.PayerCaution;


public class PCautionForm {
  private PayerCaution pc=new PayerCaution(); 
  private List<PayerCaution>listePCaution=new ArrayList<PayerCaution>();

    public PayerCaution getPc() {
        return pc;
    }

    public void setPc(PayerCaution pc) {
        this.pc = pc;
    }

    public List<PayerCaution> getListePCaution() {
        return listePCaution;
    }

    public void setListePCaution(List<PayerCaution> listePCaution) {
        this.listePCaution = listePCaution;
    }
  
}
