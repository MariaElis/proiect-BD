package atelier_gfx;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import atelier_core.Angajat;
import atelier_core.CheckFormat;

import atelier_dao.AngajatDAO;

import atelier_dao.DepartamentDAO;
import atelier_core.Departament;

public class AddAngajatDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldNume;
    private JTextField textFieldPrenume;
    private JTextField textFieldSalariu;
    private JTextField textFieldDataAngajare;
    private JTextField textFieldIdDepartament;

    private String nume = null;
    private String prenume = null;
    private Float salariu = null;
    private Date data_angajare = null;
    private Integer id_departament = null;

    private AngajatDAO angajatDao = null;
    private AtelierManagerApp mainFrame = null;
    private Angajat previousAngajat = null;
    private boolean updateMode = false;

    public AddAngajatDialog(AngajatDAO angajatDao, AtelierManagerApp mainFrame, Angajat previousAngajat,
                            boolean updateMode) {
        this();
        this.angajatDao = angajatDao;
        this.mainFrame = mainFrame;
        this.previousAngajat = previousAngajat;
        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Update Angajat");
            populateGui(previousAngajat);
        }
    }

    private void populateGui(Angajat previousAngajat2) {
        textFieldNume.setText(previousAngajat2.getNume());
        textFieldPrenume.setText(previousAngajat2.getPrenume());
        textFieldSalariu.setText(String.valueOf(previousAngajat2.getSalariu()));
        textFieldDataAngajare.setText(previousAngajat2.getData_angajare().toString());
        textFieldIdDepartament.setText(String.valueOf(previousAngajat2.getId_departament()));
    }

    public AddAngajatDialog(AngajatDAO angajatDao, AtelierManagerApp mainFrame) {
        this(angajatDao, mainFrame, null, false);
    }

    public AddAngajatDialog() {
        setTitle("Add Angajat");
        setBounds(100, 100, 410, 292);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNume = new JLabel("Nume");
        lblNume.setBounds(86, 12, 36, 20);
        lblNume.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblNume);
        textFieldNume = new JTextField();
        textFieldNume.setBounds(132, 11, 262, 23);
        textFieldNume.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldNume);
        textFieldNume.setColumns(10);

        JLabel lblPrenume = new JLabel("Prenume");
        lblPrenume.setBounds(64, 43, 60, 17);
        lblPrenume.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblPrenume);
        textFieldPrenume = new JTextField();
        textFieldPrenume.setBounds(132, 39, 262, 23);
        textFieldPrenume.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldPrenume);
        textFieldNume.setColumns(10);


        JLabel lblSalariu = new JLabel("Salariu");
        lblSalariu.setBounds(78, 70, 44, 17);
        lblSalariu.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblSalariu);
        textFieldSalariu = new JTextField();
        textFieldSalariu.setBounds(132, 67, 262, 23);
        textFieldSalariu.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldSalariu);
        textFieldSalariu.setColumns(10);

        JLabel lblDataAngajare = new JLabel("Data Angajare");
        lblDataAngajare.setBounds(23, 101, 99, 17);
        lblDataAngajare.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblDataAngajare);
        textFieldDataAngajare = new JTextField();
        textFieldDataAngajare.setBounds(132, 98, 262, 23);
        textFieldDataAngajare.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldDataAngajare);
        textFieldDataAngajare.setColumns(10);


        JLabel lblIdDepartament = new JLabel("Id Departament");
        lblIdDepartament.setBounds(10, 130, 112, 17);
        lblIdDepartament.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblIdDepartament);
        textFieldIdDepartament = new JTextField();
        textFieldIdDepartament.setBounds(132, 127, 262, 23);
        textFieldIdDepartament.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldIdDepartament);
        textFieldIdDepartament.setColumns(10);


        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        saveAngajat();
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
                    AddAngajatDialog.this.setVisible(false);
                    AddAngajatDialog.this.dispose();
                }
            });
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }

    //validam datele angajatului
    private void saveAngajat() throws SQLException {
        boolean validData = true;
        if (!textFieldNume.getText().isEmpty()) {
            nume = textFieldNume.getText();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Nume este sir vid", "Error", JOptionPane.ERROR_MESSAGE);
            validData = false;
        }
        if (validData) {
            if (!textFieldPrenume.getText().isEmpty()) {
                prenume = textFieldPrenume.getText();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Prenume este sir vid", "Error", JOptionPane.ERROR_MESSAGE);
                validData = false;
            }
        }

        if (validData) {
            if (!textFieldSalariu.getText().isEmpty()) {

                if (CheckFormat.isFloat(textFieldSalariu.getText())) {
                    salariu = Float.parseFloat(textFieldSalariu.getText());
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Salariul nu este numar real", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    validData = false;
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Salariul este sir vid", "Error", JOptionPane.ERROR_MESSAGE);
                validData = false;
            }
        }

        if (validData) {

            if (!textFieldDataAngajare.getText().isEmpty()) {

                if (CheckFormat.isDateValid(textFieldDataAngajare.getText())) {
                    data_angajare = Date.valueOf(textFieldDataAngajare.getText());
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Data nu exista sau nu respecta formatul yyyy-mm-dd ",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    validData = false;
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Data angajare este sir vid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                validData = false;
            }
        }

        if (validData) {
            if (!textFieldIdDepartament.getText().isEmpty()) {

                if (CheckFormat.isInteger(textFieldIdDepartament.getText())) {
                    id_departament = Integer.parseInt(textFieldIdDepartament.getText());
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Id Departament nu este numar intreg", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    validData = false;
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Id Departament este sir vid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                validData = false;
            }
        }


        if (validData) {
            Angajat tempAngajat = null;
            if (updateMode) {
                tempAngajat = previousAngajat;
                tempAngajat.setNume(nume);
                tempAngajat.setPrenume(prenume);
                tempAngajat.setSalariu(salariu);
                tempAngajat.setData_angajare(data_angajare);
                tempAngajat.setId_departament(id_departament);
            } else
                tempAngajat = new Angajat(angajatDao.getLastId() + 1, nume, prenume, salariu, data_angajare,
                        id_departament);

            if (updateMode) {
                angajatDao.updateAngajat(tempAngajat);

            } else
                angajatDao.addAngajatToDatabase(tempAngajat);
            if (!AngajatDAO.sqlError) {
                setVisible(false);
                dispose();
                mainFrame.refreshAngajatTable();

                JOptionPane.showMessageDialog(mainFrame, "Angajat adaugat cu succes", "Angajat adaugat",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }
}
