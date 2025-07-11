package om.esi2.mini_projet.model;

import java.io.Serializable;

public class FraisTransport implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transport; // "train", "avion", "voiture"

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }
}
