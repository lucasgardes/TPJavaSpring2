package om.esi2.mini_projet.model;

import java.io.Serializable;

public class FraisRestauration implements Serializable {
    private static final long serialVersionUID = 1L;
     private String restauration; // "aucun" ou "payant"

    public String getRestauration() {
        return restauration;
    }

    public void setRestauration(String restauration) {
        this.restauration = restauration;
    }
}
