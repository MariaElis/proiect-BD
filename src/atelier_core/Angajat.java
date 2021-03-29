package atelier_core;

import java.sql.Date;

public class Angajat {
    private int id_angajat;
    private String nume;
    private String prenume;
    private Float salariu;
    private Date data_angajare;
    private int id_departament;


    public Angajat(int id_angajat,String nume,String prenume,Float salariu,Date data_angajare,int id_departament) {
        this.id_angajat=id_angajat;
        this.nume=nume;
        this.prenume=prenume;

        this.salariu=salariu;
        this.data_angajare=data_angajare;

        this.id_departament=id_departament;

    }

    public Angajat(String nume,String prenume,Float salariu,Date data_angajare,int id_departament){
        this.nume=nume;
        this.prenume=prenume;

        this.salariu=salariu;
        this.data_angajare=data_angajare;

        this.id_departament=id_departament;

    }

    public int getId_angajat() {
        return id_angajat;
    }

    public void setId_angajat(int cod_angajat) {
        this.id_angajat = id_angajat;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Float getSalariu() {
        return salariu;
    }

    public void setSalariu(Float salariu) {
        this.salariu = salariu;
    }

    public Date getData_angajare() {
        return data_angajare;
    }

    public void setData_angajare(Date data_angajare) {
        this.data_angajare = data_angajare;
    }

    public int getId_departament() {
        return id_departament;
    }

    public void setId_departament(int id_departament) {
        this.id_departament = id_departament;
    }


}
