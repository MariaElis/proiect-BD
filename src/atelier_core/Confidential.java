package atelier_core;

public class Confidential {
    private int id_confidential;
    private int id_angajat;
    private String cnp;
    private String telefon;
    private String adresa;

    public Confidential(int id_confidential,int id_angajat,String cnp,String telefon,String adresa) {
        this.id_confidential=id_confidential;
        this.id_angajat=id_angajat;
        this.cnp=cnp;
        this.telefon=telefon;
        this.adresa=adresa;

    }

    public int getId_confidential() {
        return id_confidential;
    }

    public void setId_confidential(int id_confidential) {
        this.id_confidential = id_confidential;
    }

    public int getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(int id_angajat) {
        this.id_angajat = id_angajat;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
