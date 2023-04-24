package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database {

    public Database() {
        Employee emp = new Employee(1, "Marduk Árpád", "Miskolc", 395.);
      //  this.insertEmployee(emp);
      ArrayList<Employee> emplist = this.getEmployees();
      emplist.forEach((employee) -> {

     
      System.out.println(employee.name);
    });
     
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
    public void tryinsertEmployee(Employee emp) throws SQLException, ClassNotFoundException {

        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/hum";

        Class.forName("org.mariadb.jdbc.Driver");
        con = DriverManager.getConnection(url, "hum", "titok");
        System.out.println("működik");
        String sql = " insert into employees" +
                "(name, city, salary) values" +
                "(?,?,?)";
        // 'Pali','Szeged', 347
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, emp.name);
        pstmt.setString(2, emp.city);
        pstmt.setDouble(3, emp.salary);
        System.out.println(pstmt.toString());
        pstmt.execute();
        con.close();
        

    } 
    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> emplist;
        try {
           emplist = trygetEmployees();
        } catch (Exception e) {
            System.err.println("Hiba!A dolgozók lekérdezése sikertelen!");
          emplist = null;
        }
        return emplist;
    }
    public ArrayList<Employee> trygetEmployees() 
    throws ClassNotFoundException, SQLException{
        ArrayList<Employee> emplist = new ArrayList<>();
        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/hum";

        Class.forName("org.mariadb.jdbc.Driver");
        con = DriverManager.getConnection(url, "hum", "titok");
        System.out.println("működik");
        String sql = "select * from employees";
        Statement stsmt = con.createStatement();
        ResultSet rs = stsmt.executeQuery(sql);
        emplist = convertRestoList(rs);
        return emplist;
    }
    public ArrayList<Employee> convertRestoList(ResultSet rs) throws SQLException{
        ArrayList<Employee> emplist = new ArrayList<>();
        while(rs.next()){
            Employee emp = new Employee(rs.getInt("id"),
             rs.getString("name"), rs.getString("city"), rs.getDouble("salary"));

             emplist.add(emp);
        }
        return emplist;
    }
}