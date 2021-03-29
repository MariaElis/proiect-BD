package atelier_gfx;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import atelier_core.CheckFormat;
import atelier_core.Confidential;

import atelier_dao.ConfidentialDAO;

public class AddConfidentialDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldIdAngajat;
    private JTextField textFieldCnp;
    private JTextField textFieldTelefon;
    private JTextField textFieldAdresa;

    private Integer id_angajat = null;
    private String cnp = null;
    private String telefon = null;
    private String adresa = null;


    private ConfidentialDAO confidentialDao = null;
    private AtelierManagerApp mainFrame = null;
    private Confidential previousConfidential = null;
    private boolean updateMode = false;

    public AddConfidentialDialog(ConfidentialDAO confidentialDao, AtelierManagerApp mainFrame,
                                 Confidential previousConfidential, boolean updateMode) {
        this();
        this.confidentialDao = confidentialDao;
        this.mainFrame = mainFrame;
        this.previousConfidential = previousConfidential;
        this.updateMode = updateMode;

        if (updateMode) {
            setTitle("Update Confidential Data");
            populateGui(previousConfidential);
        }
    }

    private void populateGui(Confidential previousAngajat2) {
        textFieldIdAngajat.setText(String.valueOf(previousAngajat2.getId_angajat()));
        textFieldCnp.setText(previousAngajat2.getCnp());
        textFieldTelefon.setText(previousAngajat2.getTelefon());
        textFieldAdresa.setText(previousAngajat2.getAdresa());

    }

    public AddConfidentialDialog(ConfidentialDAO confidentialDao, AtelierManagerApp mainFrame) {
        this(confidentialDao, mainFrame, null, false);
    }

    public AddConfidentialDialog() {
        setTitle("Add Confidential Data");
        setBounds(100, 100, 410, 219);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblIdAngajat = new JLabel("Cod Angajat");
        lblIdAngajat.setBounds(38, 98, 78, 17);
        lblIdAngajat.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblIdAngajat);
        textFieldIdAngajat = new JTextField();
        textFieldIdAngajat.setBounds(132, 98, 262, 23);
        textFieldIdAngajat.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldIdAngajat);
        textFieldIdAngajat.setColumns(10);

        JLabel lblCnp = new JLabel("Cnp");
        lblCnp.setBounds(85, 12, 31, 20);
        lblCnp.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblCnp);
        textFieldCnp = new JTextField();
        textFieldCnp.setBounds(132, 11, 262, 23);
        textFieldCnp.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldCnp);
        textFieldCnp.setColumns(10);

        JLabel lblTelefon = new JLabel("Telefon");
        lblTelefon.setBounds(64, 43, 52, 17);
        lblTelefon.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblTelefon);
        textFieldTelefon = new JTextField();
        textFieldTelefon.setBounds(132, 39, 262, 23);
        textFieldTelefon.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldTelefon);
        textFieldTelefon.setColumns(10);

        JLabel lblAdresa = new JLabel("Adresa");
        lblAdresa.setBounds(64, 70, 52, 17);
        lblAdresa.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(lblAdresa);
        textFieldAdresa = new JTextField();
        textFieldAdresa.setBounds(132, 67, 262, 23);
        textFieldAdresa.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(textFieldAdresa);
        textFieldAdresa.setColumns(10);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        saveConfidential();
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
                    AddConfidentialDialog.this.setVisible(false);
                    AddConfidentialDialog.this.dispose();
                }
            });
            cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }

    private void saveConfidential() throws SQLException {
        boolean validData = true;
        if (validData) {
            if (!textFieldIdAngajat.getText().isEmpty()) {

                if (CheckFormat.isInteger(textFieldIdAngajat.getText())) {
                    id_angajat = Integer.parseInt(textFieldIdAngajat.getText());
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Id Angajat nu este numar intreg", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    validData = false;
                }
            }
            else {
                JOptionPane.showMessageDialog(mainFrame, "Id Angajat este sir vid", "Error", JOptionPane.ERROR_MESSAGE);
                validData = false;
            }
        }

        if (!textFieldCnp.getText().isEmpty()) {
            if (textFieldCnp.getText().length() == 13 && textFieldCnp.getText().matches("[0-9]+")) {
                cnp = textFieldCnp.getText();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Cnp nu are 13 cifre sau contine caractere non-numerice",
                        "Error", JOptionPane.ERROR_MESSAGE);
                validData = false;
            }

        } else {
            JOptionPane.showMessageDialog(mainFrame, "Cnp este sir vid", "Error", JOptionPane.ERROR_MESSAGE);
            validData = false;
        }
        if (validData) {
            if (!textFieldTelefon.getText().isEmpty()) {
                if (textFieldTelefon.getText().matches("[0-9]+")) {
                    telefon = textFieldTelefon.getText();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Telefonul contine doar cifre", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    validData = false;
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Telefon este sir vid", "Error", JOptionPane.ERROR_MESSAGE);
                validData = false;
            }
        }
        adresa = textFieldAdresa.getText();


        if (validData) {
            Confidential tempConfidential = null;
            if (updateMode) {
                tempConfidential = previousConfidential;
                tempConfidential.setId_angajat(id_angajat);
                tempConfidential.setCnp(cnp);
                tempConfidential.setTelefon(telefon);
                tempConfidential.setAdresa(adresa);


            } else {
                tempConfidential = new Confidential(confidentialDao.getLastId() + 1, id_angajat , cnp, telefon, adresa);
            }

            if (updateMode) {
                confidentialDao.updateConfidential(tempConfidential);

            } else
                confidentialDao.addConfidentialToDatabase(tempConfidential);
            if (!ConfidentialDAO.sqlError) {
                setVisible(false);
                dispose();
                mainFrame.refreshConfidentialTable();

                JOptionPane.showMessageDialog(mainFrame, "Date confidentiale adaugate cu succes",
                        "Date confidentiale adaugate", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }
}

