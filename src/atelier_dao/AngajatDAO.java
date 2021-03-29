package atelier_dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import atelier_core.Angajat;
import atelier_gfx.AtelierManagerApp;

import javax.swing.*;


public class AngajatDAO {
    private Connection myConn;
    private AtelierManagerApp mainFrame = null;
    public static boolean sqlError;

    public AngajatDAO(AtelierManagerApp mainFrame) {
        Properties props = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            props.load(classLoader.getResourceAsStream("atelier.properties"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String dburl = props.getProperty("dburl");
        props.setProperty("useSSL", "false");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                myConn = DriverManager.getConnection(dburl, props);
                System.out.println("Conected to Oracle");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            this.mainFrame = mainFrame;

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    public List<Angajat> getAllAngajati() throws SQLException {
        List<Angajat> list = new ArrayList<Angajat>();
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from ANGAJAT order by id_angajat");
            while (myRs.next()) {
                Angajat tempAngajat = convertRowToAngajat(myRs);
                list.add(tempAngajat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            close(myStmt, myRs);
        }

        return list;

    }

    public List<Angajat> SearchAngajat(String nume) throws SQLException {
        List<Angajat> listAngajati = new ArrayList<Angajat>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            nume += "%";
            myStmt = myConn.prepareStatement("select * from angajat where nume like ?");
            myStmt.setString(1, nume);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                Angajat tempAngajat = convertRowToAngajat(myRs);
                listAngajati.add(tempAngajat);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myRs);
        }
        return listAngajati;
    }

    private void close(Connection myConn2, PreparedStatement myStmt, ResultSet myRs) throws SQLException {

        if (myConn2 != null)
            myStmt.close();
        if (myStmt != null)
            myStmt.close();
        if (myRs != null)
            myRs.close();

    }

    private void close(Connection myConn2, CallableStatement myStmt, ResultSet myRs) throws SQLException {

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

    private Angajat convertRowToAngajat(ResultSet myRs) throws SQLException {
        int id_angajat = myRs.getInt("id_angajat");
        String nume = myRs.getString("nume");
        String prenume = myRs.getString("prenume");
        float salariu = myRs.getInt("salariu");
        Date data_angajare = myRs.getDate("data_angajare");
        int id_departament = myRs.getInt("id_departament");
        return new Angajat(id_angajat, nume, prenume,  salariu, data_angajare,  id_departament);
    }

    public void addAngajatToDatabase(Angajat theAngajat) throws SQLException {
        PreparedStatement myStmt=null;
        try {
            myStmt=myConn.prepareStatement("insert into angajat"+"(id_angajat,nume, prenume, salariu, data_angajare, id_departament)"+"values(?,?,?,?,?,?)");
            myStmt.setInt(1, getLastId()+1);
            myStmt.setString(2, theAngajat.getNume());
            myStmt.setString(3, theAngajat.getPrenume());
            myStmt.setFloat(4, theAngajat.getSalariu());
            myStmt.setDate(5, theAngajat.getData_angajare());
            myStmt.setInt(6, theAngajat.getId_departament());
            myStmt.executeUpdate();
            sqlError = false;
        }
        catch (SQLException e) {

            String message = e.getMessage();
            if (message.length() > 65) {
                message = message.substring(0, 64);
            }
            JOptionPane.showMessageDialog(mainFrame, "Eroare salvare date confidentiale:\n" + message, "Error",
                    JOptionPane.ERROR_MESSAGE);
            sqlError = true;
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public void updateAngajat(Angajat theAngajat) throws SQLException {
        PreparedStatement myStmt=null;
        try {

            myStmt=myConn.prepareStatement("update angajat"
                    + " set nume=?, prenume=?, salariu=?, data_angajare=?, id_departament=?"
                    + " where id_angajat=?");
            myStmt.setString(1, theAngajat.getNume());
            myStmt.setString(2, theAngajat.getPrenume());
            myStmt.setFloat(3, theAngajat.getSalariu());
            myStmt.setDate(4, theAngajat.getData_angajare());
            myStmt.setInt(5, theAngajat.getId_departament());
            myStmt.setInt(6,theAngajat.getId_angajat());
            myStmt.executeUpdate();
            sqlError = false;

        }
        catch(Exception e)
        {
            String message = e.getMessage();
            if (message.length() > 65) {
                message = message.substring(0, 64);
            }
            JOptionPane.showMessageDialog(mainFrame, "Eroare salvare angajat:\n" + message, "Error",
                    JOptionPane.ERROR_MESSAGE);
            sqlError = true;
        }
        finally {
            close(myStmt, null);
        }
    }

    public void deleteAngajat(int id_angajat) throws SQLException {
        PreparedStatement myStmt=null;
        try {

            myStmt=myConn.prepareStatement("delete from angajat where id_angajat=?");
            myStmt.setInt(1, id_angajat);
            myStmt.executeUpdate();
            sqlError = false;
        }
        finally {
            close(myStmt, null);
        }
    }

    public void updateID(int idDeletedAngajat) throws SQLException
    {

        PreparedStatement myStmt=null;
        ResultSet myRs=null;

        try
        {
            int count;
            myStmt=myConn.prepareStatement("select count(*)  FROM angajat where id_angajat>?" );
            myStmt.setInt(1, idDeletedAngajat);
            myRs=myStmt.executeQuery();
            myRs.next();
            count=myRs.getInt(1);

            for(int i=0;i<count;i++)
            {
                myStmt=myConn.prepareStatement("update angajat set id_angajat=? where id_angajat=?");

                myStmt.setInt(1, idDeletedAngajat);
                myStmt.setInt(2, idDeletedAngajat+1);
                myStmt.executeUpdate();
                idDeletedAngajat++;
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(myStmt,null);
        }

    }
    public int getLastId() throws SQLException
    {
        Statement myStmt=null;
        ResultSet myRs=null;
        Angajat tempAngajat=null;

        try
        {
            myStmt=myConn.createStatement();
            myRs=myStmt.executeQuery("select * from angajat ");
            while(myRs.next())
            {
                tempAngajat=convertRowToAngajat(myRs);
            }
            if(tempAngajat!=null)
                return tempAngajat.getId_angajat();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(myStmt,null);
        }

        return 0;

    }



}
