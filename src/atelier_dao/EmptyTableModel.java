package atelier_dao;

import javax.swing.table.AbstractTableModel;

public class EmptyTableModel extends AbstractTableModel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

}
