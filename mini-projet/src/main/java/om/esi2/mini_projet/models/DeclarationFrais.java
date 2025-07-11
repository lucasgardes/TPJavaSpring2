package om.esi2.mini_projet.models;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "declarations_frais")
public class DeclarationFrais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String intituleFormation;
    private String lieuFormation;

    private LocalDateTime dateFormation;

    private String typeTransport;
    private String typeHebergement;
    private String typeRestauration;

    private String iban;
    private String bic;

    private String statut;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getIntituleFormation() { return intituleFormation; }
    public void setIntituleFormation(String intituleFormation) { this.intituleFormation = intituleFormation; }

    public String getLieuFormation() { return lieuFormation; }
    public void setLieuFormation(String lieuFormation) { this.lieuFormation = lieuFormation; }

    public LocalDateTime getDateFormation() { return dateFormation; }
    public void setDateFormation(LocalDateTime dateFormation) { this.dateFormation = dateFormation; }

    public String getTypeTransport() { return typeTransport; }
    public void setTypeTransport(String typeTransport) { this.typeTransport = typeTransport; }

    public String getTypeHebergement() { return typeHebergement; }
    public void setTypeHebergement(String typeHebergement) { this.typeHebergement = typeHebergement; }

    public String getTypeRestauration() { return typeRestauration; }
    public void setTypeRestauration(String typeRestauration) { this.typeRestauration = typeRestauration; }

    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }

    public String getBic() { return bic; }
    public void setBic(String bic) { this.bic = bic; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
