package atelier_dao;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier_core.Angajat;

public class AngajatTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    private static final int ID_ANGAJAT_COL=0;
    private static final int NUME_COL=1;
    private static final int PRENUME_COL=2;
    private static final int SALARIU_COL=3;
    private static final int DATA_ANGAJARE_COL=4;
    private static final int ID_DEPARTAMENT_COL=5;

    private String[] columnName={"Id Angajat","Nume","Prenume","Salariu","Data Angajare","Id Departament"};
    private List<Angajat>listAngajati;

    public AngajatTableModel( List<Angajat> theAngajati) {
        listAngajati=theAngajati;
    }

    @Override
    public int getColumnCount() {

        return columnName.length;
    }

    @Override
    public int getRowCount() {

        return listAngajati.size();
    }

    @Override
    public String getColumnName(int col)
    {
        return columnName[col];
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Angajat tempAngajat=listAngajati.get(row);
        switch(col)
        {
            case ID_ANGAJAT_COL:
                return tempAngajat.getId_angajat();
            case NUME_COL:
                return tempAngajat.getNume();
            case PRENUME_COL:
                return tempAngajat.getPrenume();
            case SALARIU_COL:
                return tempAngajat.getSalariu();
            case DATA_ANGAJARE_COL:
                return tempAngajat.getData_angajare();
            case ID_DEPARTAMENT_COL:
                return tempAngajat.getId_departament();
            case OBJECT_COL:
                return tempAngajat;
            default:
                return tempAngajat.getNume();

        }
    }
}
