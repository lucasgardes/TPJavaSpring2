package om.esi2.mini_projet.model;

import java.io.Serializable;

public class FraisHebergement implements Serializable {
    private static final long serialVersionUID = 1L;
    private String hebergement; // "gratuit" ou "payant"

    public String getHebergement() {
        return hebergement;
    }

    public void setHebergement(String hebergement) {
        this.hebergement = hebergement;
    }
}
