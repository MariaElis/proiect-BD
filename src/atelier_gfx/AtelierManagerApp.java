package atelier_gfx;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import atelier_core.*;
import atelier_dao.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class AtelierManagerApp extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldName;
    private JButton btnSearch;

    private JScrollPane scrollPane;
    private JTable table;

    private AngajatDAO angajatDao;
    private DepartamentDAO departamentDao;
    private ConfidentialDAO confidentialDao;

    private JPanel panel_1;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JComboBox<String> selectedTable;
    private JLabel lblTable;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel lblSearchBy;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AtelierManagerApp frame = new AtelierManagerApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AtelierManagerApp() {
        try {

            departamentDao=new DepartamentDAO();
            angajatDao= new AngajatDAO(AtelierManagerApp.this);
            confidentialDao= new ConfidentialDAO(AtelierManagerApp.this);


        } catch (Exception e1) {

            JOptionPane.showMessageDialog(this, "Error" + e1, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Atelier Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.WEST);

        lblTable = new JLabel("Tabel");
        panel_2.add(lblTable);
        lblTable.setFont(new Font("Arial", Font.PLAIN, 14));

        selectedTable = new JComboBox<String>();
        selectedTable.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                table.setModel(new EmptyTableModel());
                String searchBy="Cauta dupa ";
                String tableName = (String) selectedTable.getSelectedItem();
                switch (tableName) {
                    case "departament": {
                        lblSearchBy.setText(searchBy+"departament");
                        break;
                    }
                    case "angajat": {
                        lblSearchBy.setText(searchBy+"nume");
                        break;
                    }
                    case "confidential": {
                        lblSearchBy.setText(searchBy+"cnp");
                        break;
                    }
                }

            }
        });
        panel_2.add(selectedTable);
        selectedTable.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedTable.setToolTipText("");
        selectedTable
                .setModel(new DefaultComboBoxModel<String>(new String[] {"angajat","departament","confidential"}));
        selectedTable.setSelectedIndex(0);

        panel_3 = new JPanel();
        panel.add(panel_3, BorderLayout.EAST);

        lblSearchBy = new JLabel("Cauta dupa nume");
        panel_3.add(lblSearchBy);
        lblSearchBy.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSearchBy.setHorizontalAlignment(SwingConstants.LEFT);

        textFieldName = new JTextField();
        panel_3.add(textFieldName);
        textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
        textFieldName.setColumns(15);

        btnSearch = new JButton("Search");
        panel_3.add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchWord = textFieldName.getText();
                    String tableName = (String) selectedTable.getSelectedItem();
                    switch (tableName) {
                        case "departament": {
                            List<Departament> list = null;
                            if (searchWord != null && searchWord.trim().length() > 0)
                                list = departamentDao.SearchDepartament(searchWord);
                            else
                                list = departamentDao.getAllDepartamente();
                            DepartamentTableModel model = new DepartamentTableModel(list);
                            table.setModel(model);
                            break;
                        }
                        case "angajat": {
                            List<Angajat> list = null;
                            if (searchWord != null && searchWord.trim().length() > 0)
                                list = angajatDao.SearchAngajat(searchWord);
                            else
                                list = angajatDao.getAllAngajati();
                            AngajatTableModel model = new AngajatTableModel(list);
                            table.setModel(model);
                            break;
                        }

                        case "confidential": {
                            List<Confidential> list = null;
                            if (searchWord != null && searchWord.trim().length() > 0)
                                list = confidentialDao.SearchConfidentialData(searchWord);
                            else
                                list = confidentialDao.getAllConfidentialData();
                            ConfidentialTableModel model = new ConfidentialTableModel(list);
                            table.setModel(model);
                            break;
                        }
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(AtelierManagerApp.this, "Error " + e2, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 14));

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String tableName = (String) selectedTable.getSelectedItem();
                switch (tableName) {

                    case "departament": {
                        AddDepartamentDialog dialog = new AddDepartamentDialog(departamentDao, AtelierManagerApp.this);
                        dialog.setVisible(true);
                        break;
                    }
                    case "angajat": {
                        AddAngajatDialog dialog = new AddAngajatDialog(angajatDao, AtelierManagerApp.this);
                        dialog.setVisible(true);
                        break;
                    }
                    case "confidential": {
                        AddConfidentialDialog dialog = new AddConfidentialDialog(confidentialDao, AtelierManagerApp.this);
                        dialog.setVisible(true);
                        break;
                    }

                }

            }
        });
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 14));
        panel_1.add(btnAdd);

        btnUpdate = new JButton("Update ");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(AtelierManagerApp.this, "Trebuie sa selectezi o intrare !", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String tableName = (String) selectedTable.getSelectedItem();
                switch (tableName) {
                    case "departament": {
                        Departament tempDepartament = (Departament) table.getValueAt(row, DepartamentTableModel.OBJECT_COL);
                        AddDepartamentDialog dialog = new AddDepartamentDialog(departamentDao, AtelierManagerApp.this, tempDepartament, true);
                        dialog.setVisible(true);
                        break;
                    }
                    case "angajat": {
                        Angajat tempAngajat = (Angajat) table.getValueAt(row, AngajatTableModel.OBJECT_COL);
                        AddAngajatDialog dialog = new AddAngajatDialog(angajatDao, AtelierManagerApp.this, tempAngajat, true);
                        dialog.setVisible(true);
                        break;
                    }
                    case "confidential": {
                        Confidential tempConfidential = (Confidential) table.getValueAt(row, ConfidentialTableModel.OBJECT_COL);
                        AddConfidentialDialog dialog = new AddConfidentialDialog(confidentialDao, AtelierManagerApp.this, tempConfidential, true);
                        dialog.setVisible(true);
                        break;
                    }

                }
            }
        });
        btnUpdate.setFont(new Font("Arial", Font.PLAIN, 14));
        panel_1.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = table.getSelectedRow();
                    if (row < 0) {
                        JOptionPane.showMessageDialog(AtelierManagerApp.this, "Trebuie sa selectezi o intrare !", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int response = JOptionPane.showConfirmDialog(AtelierManagerApp.this,
                            "Doresti sa stergi acesta intrare ?", "Confirm", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response != JOptionPane.YES_OPTION)
                        return;

                    String tableName = (String) selectedTable.getSelectedItem();
                    switch (tableName) {

                        case "departament": {
                            Departament tempDepartament = (Departament) table.getValueAt(row,DepartamentTableModel.OBJECT_COL);
                            departamentDao.deleteDepartament(tempDepartament.getId_departament());
                            departamentDao.updateID(tempDepartament.getId_departament());
                            refreshDepartamentTable();
                            JOptionPane.showMessageDialog(AtelierManagerApp.this, "Departament sters cu succes.", "Departament sters",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case "angajat": {
                            Angajat tempAngajat = (Angajat) table.getValueAt(row,AngajatTableModel.OBJECT_COL);
                            angajatDao.deleteAngajat(tempAngajat.getId_angajat());
                            angajatDao.updateID(tempAngajat.getId_angajat());
                            refreshAngajatTable();
                            JOptionPane.showMessageDialog(AtelierManagerApp.this, "Angajat sters cu succes.", "Angajat sters",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case "confidential": {
                            Confidential tempConfidential = (Confidential) table.getValueAt(row,ConfidentialTableModel.OBJECT_COL);
                            confidentialDao.deleteConfidential(tempConfidential.getId_confidential());
                            confidentialDao.updateID(tempConfidential.getId_confidential());
                            refreshConfidentialTable();
                            JOptionPane.showMessageDialog(AtelierManagerApp.this, "Date confidentiale sterse cu succes.", "Date confidentiale sterse",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                    }

                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(AtelierManagerApp.this,
                            "Eroare la stergere: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 14));
        panel_1.add(btnDelete);
    }


    public void refreshDepartamentTable() {
        try {
            List<Departament> list = departamentDao.getAllDepartamente();
            DepartamentTableModel model = new DepartamentTableModel(list);
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(AtelierManagerApp.this, "Error" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void refreshAngajatTable() {
        try {
            List<Angajat> list = angajatDao.getAllAngajati();
            AngajatTableModel model = new AngajatTableModel(list);
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(AtelierManagerApp.this, "Error" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    public void refreshConfidentialTable() {
        try {
            List<Confidential> list = confidentialDao.getAllConfidentialData();
            ConfidentialTableModel model = new ConfidentialTableModel(list);
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(AtelierManagerApp.this, "Error" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }


}
