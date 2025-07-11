package om.esi2.mini_projet.model;

import java.io.Serializable;

public class CoordonneesBancaires implements Serializable {
    private static final long serialVersionUID = 1L;
    private String iban;
    private String bic;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
}
