package atelier_dao;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import atelier_core.Confidential;
import atelier_gfx.AtelierManagerApp;

public class ConfidentialDAO {
    private Connection myConn;
    private AtelierManagerApp mainFrame = null;
    public static boolean sqlError = false;

    public ConfidentialDAO(AtelierManagerApp mainFrame) {
        Properties props = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            props.load(classLoader.getResourceAsStream("atelier.properties"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String dburl = props.getProperty("dburl");
        //props.setProperty("useSSL", "false");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                myConn = DriverManager.getConnection(dburl, props);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            this.mainFrame = mainFrame;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    public List<Confidential> getAllConfidentialData() throws SQLException {
        List<Confidential> list = new ArrayList<Confidential>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from confidential order by id_confidential");
            while (myRs.next()) {
                Confidential tempConfidential = convertRowToConfidential(myRs);
                list.add(tempConfidential);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            close(myStmt, myRs);
        }

        return list;

    }

    public List<Confidential> SearchConfidentialData(String nume) throws SQLException {
        List<Confidential> listConfidential = new ArrayList<Confidential>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            nume += "%";
            myStmt = myConn.prepareStatement("select * from confidential where cnp like ?");
            myStmt.setString(1, nume);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                Confidential tempConfidential = convertRowToConfidential(myRs);
                listConfidential.add(tempConfidential);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
        return listConfidential;
    }

    private void close(Connection myConn2, PreparedStatement myStmt, ResultSet myRs) throws SQLException {

        if (myConn2 != null)
            myStmt.close();
        if (myStmt != null)
            myStmt.close();
        if (myRs != null)
            myRs.close();

    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        if (myStmt != null)
            myStmt.close();
        if (myRs != null)
            myRs.close();
    }

    private Confidential convertRowToConfidential(ResultSet myRs) throws SQLException {
        int id_confidential = myRs.getInt("id_confidential");
        int id_angajat = myRs.getInt("id_angajat");
        String cnp = myRs.getString("cnp");
        String telefon = myRs.getString("telefon");
        String adresa = myRs.getString("adresa");

        return new Confidential(id_confidential,id_angajat, cnp, telefon, adresa);
    }

    public void addConfidentialToDatabase(Confidential theConfidential) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            myStmt = myConn.prepareStatement("insert into confidential"
                    + "(id_confidential, id_angajat,cnp, telefon, adresa)" + "values(?,?,?,?,?)");
            myStmt.setInt(1, getLastId() + 1);
            myStmt.setInt(2, theConfidential.getId_angajat());
            myStmt.setString(3, theConfidential.getCnp());
            myStmt.setString(4, theConfidential.getTelefon());
            myStmt.setString(5, theConfidential.getAdresa());

            myStmt.executeUpdate();
            sqlError = false;
        } catch (SQLException e) {
            String message = e.getMessage();
            if (message.length() > 65) {
                message = message.substring(0, 64);
            }
            JOptionPane.showMessageDialog(mainFrame, "Eroare salvare date confidentiale:\n" + message, "Error",
                    JOptionPane.ERROR_MESSAGE);
            sqlError = true;
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void updateConfidential(Confidential theConfidential) throws SQLException {
        PreparedStatement myStmt = null;
        try {

            myStmt = myConn.prepareStatement("update confidential"
                    + " set id_angajat=?, cnp=?, telefon=?, adresa=?" + " where id_confidential=?");
            myStmt.setInt(1, theConfidential.getId_angajat());
            myStmt.setString(2, theConfidential.getCnp());
            myStmt.setString(3, theConfidential.getTelefon());
            myStmt.setString(4, theConfidential.getAdresa());
            myStmt.setInt(5, theConfidential.getId_confidential());
            myStmt.executeUpdate();
            sqlError = false;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.length() > 65) {
                message = message.substring(0, 64);
            }
            JOptionPane.showMessageDialog(mainFrame, "Eroare salvare date confidentiale:\n" + message, "Error",
                    JOptionPane.ERROR_MESSAGE);
            sqlError = true;
        } finally {
            close(myStmt, null);
        }
    }

    public void deleteConfidential(int cod_confidential) throws SQLException {
        PreparedStatement myStmt = null;
        try {

            myStmt = myConn.prepareStatement("delete from confidential where id_confidential=?");
            myStmt.setInt(1, cod_confidential);
            myStmt.executeUpdate();
            sqlError = false;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.length() > 65) {
                message = message.substring(0, 64);
            }
            JOptionPane.showMessageDialog(mainFrame, "Eroare salvare date confidentiale:\n" + message, "Error",
                    JOptionPane.ERROR_MESSAGE);
            sqlError = true;
        } finally {
            close(myStmt, null);
        }
    }

    public void updateID(int idDeletedConfidential) throws SQLException {

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            int count;
            myStmt = myConn.prepareStatement("select count(*)  FROM confidential where id_confidential>?");
            myStmt.setInt(1, idDeletedConfidential);
            myRs = myStmt.executeQuery();
            myRs.next();
            count = myRs.getInt(1);

            for (int i = 0; i < count; i++) {
                myStmt = myConn.prepareStatement("update angajat set id_confidential=? where id_confidential=?");

                myStmt.setInt(1, idDeletedConfidential);
                myStmt.setInt(2, idDeletedConfidential + 1);
                myStmt.executeUpdate();
                idDeletedConfidential++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myStmt, null);
        }

    }

    public int getLastId() throws SQLException {
        Statement myStmt = null;
        ResultSet myRs = null;
        Confidential tempConfidential = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from confidential");
            while (myRs.next()) {
                tempConfidential = convertRowToConfidential(myRs);
            }
            if (tempConfidential != null)
                return tempConfidential.getId_confidential();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myStmt, null);
        }

        return 0;

    }
}
