package atelier_core;

public class Departament {
    private int id_departament;
    private String nume_departament;
    private String rol;

    public Departament(int id_departament,String nume_departament,String rol) {
        this.id_departament=id_departament;
        this.nume_departament=nume_departament;
        this.rol=rol;
    }

    public int getId_departament() {
        return id_departament;
    }

    public void setId_departament(int id_departament) {
        this.id_departament = id_departament;
    }

    public String getNume_departament() {
        return nume_departament;
    }

    public void setNume_departament(String nume_departament) {
        this.nume_departament = nume_departament;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
