package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Database {

    public Database() {
        Employee emp = new Employee(1, "Marduk Árpád", "Miskolc", 395);
        this.insertEmployee(emp);

    }

    // Hibakezelő metódus
    public void insertEmployee(Employee emp) {
        try {
            tryinsertEmployee(emp);
        } catch (ClassNotFoundException e) {
            System.out.println("Hiba! Nincs MariaDB driver betöltve!");
            System.err.println(e.getMessage());

        } catch (SQLException e) {
            System.err.println("Hiba! Az adatbázishoz a kapcsolat sikertelen!");
            System.err.println(e.getMessage());
        }
    }

    // Ipari kód (hasznoskód)
    public void tryinsertEmployee(Employee emp) throws SQLException, ClassNotFoundException{
       
        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/hum";
        
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, "hum", "titok");
            System.out.println("működik");
            String sql =" insert into employees" +
            "(name, city, salary) values"+
            "(?,?,?)";
            //'Pali','Szeged', 347
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp.name);
            pstmt.setString(2, emp.city);
            pstmt.setDouble(3, emp.salary);
            System.out.println(pstmt.toString());
            pstmt.execute();
          con.close();

        
    }
}