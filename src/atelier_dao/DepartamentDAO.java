package atelier_dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import atelier_core.Departament;

public class DepartamentDAO {
    private Connection myConn;
    public DepartamentDAO()
    {
        Properties props=new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            props.load(classLoader.getResourceAsStream("atelier.properties"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String dburl=props.getProperty("dburl");
        //props.setProperty("useSSL", "false");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                myConn=DriverManager.getConnection(dburl, props);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    public List<Departament> getAllDepartamente() throws SQLException
    {
        List<Departament>list=new ArrayList<Departament>();
        Statement myStmt=null;
        ResultSet myRs=null;

        try {
            myStmt=myConn.createStatement();
            myRs=myStmt.executeQuery("select * from departament order by id_departament");
            while(myRs.next())
            {
                Departament tempDepartament=convertRowToDepartament(myRs);
                list.add(tempDepartament);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally
        {
            close(myStmt, myRs);
        }

        return list;

    }

    public List<Departament> SearchDepartament(String nume) throws SQLException
    {
        List<Departament>listDepartament=new ArrayList<Departament>();
        PreparedStatement myStmt=null;
        ResultSet myRs=null;
        try {
            nume += "%";
            myStmt=myConn.prepareStatement("select* from departament where nume_departament like ?");
            myStmt.setString(1, nume);
            myRs=myStmt.executeQuery();
            while(myRs.next())
            {
                Departament tempDepartament=convertRowToDepartament(myRs);
                listDepartament.add(tempDepartament);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        finally
        {
            close(myConn,myStmt,myRs);
        }
        return listDepartament;
    }

    private void close(Connection myConn2, PreparedStatement myStmt, ResultSet myRs) throws SQLException {

        if(myConn2!=null)
            myStmt.close();
        if(myStmt!=null)
            myStmt.close();
        if(myRs!=null)
            myRs.close();

    }

    private void close(Connection myConn2, CallableStatement myStmt, ResultSet myRs) throws SQLException {

        if(myConn2!=null)
            myStmt.close();
        if(myStmt!=null)
            myStmt.close();
        if(myRs!=null)
            myRs.close();

    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        if(myStmt!=null)
            myStmt.close();
        if(myRs!=null)
            myRs.close();

    }

    private Departament convertRowToDepartament(ResultSet myRs) throws SQLException
    {
        int id_departament = myRs.getInt("id_departament");
        String nume_departament=myRs.getString("nume_departament");
        String rol=myRs.getString("rol");
        return new Departament(id_departament, nume_departament, rol);
    }

    public void addDepartamentToDatabase(Departament theDepartament) throws SQLException
    {
        PreparedStatement myStmt=null;
        try {
            myStmt=myConn.prepareStatement("insert into departament"+"(id_departament, nume_departament, rol)"+"values(?,?,?)");
            myStmt.setInt(1, theDepartament.getId_departament());
            myStmt.setString(2, theDepartament.getNume_departament());
            myStmt.setString(3, theDepartament.getRol());
            myStmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        finally
        {
            close(myConn,myStmt,null);
        }
    }

    public void updateDepartament(Departament theDepartament)throws SQLException
    {
        PreparedStatement myStmt=null;
        try{
            myStmt=myConn.prepareStatement("update departament"
                    + " set nume_departament=?, rol=?"
                    + " where id_departament=?");
            myStmt.setString(1, theDepartament.getNume_departament());
            myStmt.setString(2, theDepartament.getRol());
            myStmt.setInt(3, theDepartament.getId_departament());
            myStmt.executeUpdate();
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

    public void deleteDepartament(int id_departament)throws SQLException
    {
        PreparedStatement myStmt=null;
        try{

            myStmt=myConn.prepareStatement("delete from departament where id_departament=?");
            myStmt.setInt(1, id_departament);
            myStmt.executeUpdate();
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

    public void updateID(int idDeletedDepartament) throws SQLException
    {

        PreparedStatement myStmt=null;
        ResultSet myRs=null;

        try
        {
            int count;

            myStmt=myConn.prepareStatement("select count(*)  FROM departament where id_departament>?" );
            myStmt.setInt(1, idDeletedDepartament);
            myRs=myStmt.executeQuery();
            myRs.next();
            count=myRs.getInt(1);

            for(int i=0;i<count;i++)
            {
                myStmt=myConn.prepareStatement("update departament set id_departament=? where id_departament=?");

                myStmt.setInt(1, idDeletedDepartament);
                myStmt.setInt(2, idDeletedDepartament+1);
                myStmt.executeUpdate();
                idDeletedDepartament++;
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
        Departament tempDepartament=null;

        try
        {
            myStmt=myConn.createStatement();
            myRs=myStmt.executeQuery("select * from departament ");
            while(myRs.next())
            {
                tempDepartament=convertRowToDepartament(myRs);
            }
            if(tempDepartament!=null)
                return tempDepartament.getId_departament();
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
