package atelier_gfx;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import atelier_core.Angajat;

import atelier_core.Departament;
import atelier_dao.AngajatDAO;

import atelier_dao.DepartamentDAO;

public class AddDepartamentDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldNumeDepartament;
    private JTextField textFieldRol;

    private String nume_departament = null;
    private String rol = null;

    private DepartamentDAO departamentDao = null;
    private AtelierManagerApp mainFrame = null;
    private Departament previousDepartament = null;
    private boolean updateMode = false;

    public AddDepartamentDialog(DepartamentDAO departamentDao, AtelierManagerApp mainFrame,
                                Departament previousDepartament, boolean updateMode) {
        this();
        this.departamentDao = departamentDao;
        this.mainFrame = mainFrame;
        this.previousDepartament = previousDepartament;
        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Update Departament");
            populateGui(previousDepartament);
        }
    }

    private void populateGui(Departament previousDepartament2) {
        textFieldNumeDepartament.setText(previousDepartament2.getNume_departament());
        textFieldRol.setText(previousDepartament2.getRol());

    }

    public AddDepartamentDialog(DepartamentDAO departamentDao, AtelierManagerApp mainFrame) {
        this(departamentDao, mainFrame, null, false);
    }

    public AddDepartamentDialog() {
        setTitle("Add Departament");
        setBounds(100, 100, 410, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNumeDepartament = new JLabel("Nume Departament");
        lblNumeDepartament.setBounds(10, 14, 122, 17);
        lblNumeDepartament.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblNumeDepartament);
        textFieldNumeDepartament = new JTextField();
        textFieldNumeDepartament.setBounds(142, 11, 242, 23);
        textFieldNumeDepartament.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldNumeDepartament);
        textFieldNumeDepartament.setColumns(10);

        JLabel lblRol = new JLabel("Rol");
        lblRol.setBounds(65, 43, 60, 17);
        lblRol.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblRol);
        textFieldRol = new JTextField();
        textFieldRol.setBounds(142, 39, 242, 23);
        textFieldRol.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldRol);
        textFieldRol.setColumns(10);



        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        saveDepartament();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            });
            saveButton.setFont(new Font("Arial", Font.PLAIN, 14));
            saveButton.setActionCommand("OK");
            buttonPane.add(saveButton);
            getRootPane().setDefaultButton(saveButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddDepartamentDialog.this.setVisible(false);
                    AddDepartamentDialog.this.dispose();
                }
            });
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }

    private void saveDepartament() throws SQLException {
        boolean validData = true;

        if (!textFieldNumeDepartament.getText().isEmpty()) {
            nume_departament = textFieldNumeDepartament.getText();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Nume departament este sir vid", "Error",
                    JOptionPane.ERROR_MESSAGE);
            validData = false;
        }

        if (!textFieldRol.getText().isEmpty()) {
            rol = textFieldRol.getText();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Rolul este sir vid", "Error",
                    JOptionPane.ERROR_MESSAGE);
            validData = false;
        }
        if (validData) {
            Departament tempDepartament = null;
            if (updateMode) {
                tempDepartament = previousDepartament;
                tempDepartament.setNume_departament(nume_departament);
                tempDepartament.setRol(rol);
            } else
                tempDepartament = new Departament(departamentDao.getLastId() + 1, nume_departament, rol);

            try {
                if (updateMode) {
                    departamentDao.updateDepartament(tempDepartament);

                } else
                    departamentDao.addDepartamentToDatabase(tempDepartament);
                setVisible(false);
                dispose();
                mainFrame.refreshDepartamentTable();

                JOptionPane.showMessageDialog(mainFrame, "Departament adaugat cu succes", "Departament adaugat",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception exc) {
                JOptionPane.showMessageDialog(mainFrame, "Eroare salvare departament: " + exc.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
