package atelier_dao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier_core.Confidential;

public class ConfidentialTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    private static final int ID_CONFIDENTIAL_COL=0;
    private static final int ID_ANGAJAT_COL=1;
    private static final int CNP_COL=2;
    private static final int TELEFON_COL=3;
    private static final int ADRESA_COL=4;
    private String[] columnName={"Id Confidential","Id Angajat","Cnp","Telefon","Adresa"};
    private List<Confidential>listConfidential;

    public ConfidentialTableModel( List<Confidential> theConfidential) {
        listConfidential=theConfidential;
    }

    @Override
    public int getColumnCount() {

        return columnName.length;
    }

    @Override
    public int getRowCount() {

        return listConfidential.size();
    }

    @Override
    public String getColumnName(int col)
    {
        return columnName[col];
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Confidential tempConfidential=listConfidential.get(row);
        switch(col)
        {
            case ID_CONFIDENTIAL_COL:
                return tempConfidential.getId_confidential();
            case ID_ANGAJAT_COL:
                return tempConfidential.getId_angajat();
            case CNP_COL:
                return tempConfidential.getCnp();
            case TELEFON_COL:
                return tempConfidential.getTelefon();
            case ADRESA_COL:
                return tempConfidential.getAdresa();

            case OBJECT_COL:
                return tempConfidential;
            default:
                return tempConfidential.getCnp();

        }
    }
}

