package ldap.learn;

public class Flux {
    private String emetteur;
    private String recepteur;
    private String message;

    public Flux(String emetteur, String recepteur, String message) {
        this.emetteur = emetteur;
        this.recepteur = recepteur;
        this.message = message;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public String getRecepteur() {
        return recepteur;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Flux{" +
                "emetteur='" + emetteur + '\'' +
                ", recepteur='" + recepteur + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

