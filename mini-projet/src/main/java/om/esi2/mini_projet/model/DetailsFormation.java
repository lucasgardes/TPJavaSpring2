package om.esi2.mini_projet.model;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class DetailsFormation implements Serializable {
    private static final long serialVersionUID = 1L;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String lieu;
    private String intitule;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
