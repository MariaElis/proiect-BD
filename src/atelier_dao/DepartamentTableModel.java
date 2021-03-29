package atelier_dao;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier_core.Departament;

public class DepartamentTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    public static final int OBJECT_COL = -1;
    private static final int ID_DEPARTAMENT_COL=0;
    private static final int NUME_DEPARTAMENT_COL=1;
    private static final int ROL_COL=2;
    private String[] columnName={"Id Departament","Nume Departament","Rol"};
    private List<Departament>listDepartament;


    public DepartamentTableModel( List<Departament> theDepartament) {
        listDepartament=theDepartament;
    }


    @Override
    public int getColumnCount() {

        return columnName.length;
    }

    @Override
    public int getRowCount() {

        return listDepartament.size();
    }

    @Override
    public String getColumnName(int col)
    {
        return columnName[col];
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Departament tempDepartament=listDepartament.get(row);
        switch(col)
        {
            case ID_DEPARTAMENT_COL:
                return tempDepartament.getId_departament();
            case NUME_DEPARTAMENT_COL:
                return tempDepartament.getNume_departament();
            case ROL_COL:
                return tempDepartament.getRol();
            case OBJECT_COL:
                return tempDepartament;
            default:
                return tempDepartament.getNume_departament();

        }
    }
}
