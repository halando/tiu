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
    public Connection connectDb(){
        Connection con = null;
        try {
           con =  tryconnectDb();
        } catch (ClassNotFoundException e) {
           System.err.println("Hiba! A driver nem található!");
           System.err.println(e.getMessage());
        } catch(SQLException e){
            System.err.println("Hiba! Az SQl utasítás végrehajtása sikertelen!");
            System.err.println(e.getMessage());
        }
        return con;
    }
    public Connection tryconnectDb() throws ClassNotFoundException, SQLException{
        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/hum";

        Class.forName("org.mariadb.jdbc.Driver");
        con = DriverManager.getConnection(url, "hum", "titok");
        System.out.println("működik");
        return con;
    }
    public void closeDb(Connection con) throws SQLException{
       
        con.close();
    }
        public void insertEmployee(Employee emp){
            try {
                tryinsertEmployee(emp);
            } catch (SQLException e) {
               System.err.println("Hiba! A rekord beszúrása sikertelen!");
            }
        }
   
    public void tryinsertEmployee(Employee emp) throws SQLException {

        Connection con = this.connectDb();
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
        this.closeDb(con);
        

    } 
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> emplist = null;
        try {
           emplist =  trygetEmployees();
        } catch (SQLException e) {
           System.err.println("Hiba! A rekordok lekérdezése sikertelen!");
        }
        return emplist;
    }
    
    public ArrayList<Employee> trygetEmployees() 
    throws  SQLException{
        ArrayList<Employee> emplist = new ArrayList<>();
        Connection con = connectDb();
        String sql = "select * from employees";
        Statement stsmt = con.createStatement();
        ResultSet rs = stsmt.executeQuery(sql);
        emplist = convertRestoList(rs);
        closeDb(con);
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
    public void deleteEmployee(int id){
        try {
            trydeleteEmployee(id);
        } catch (SQLException e) {
           System.err.println("Hiba! A rekord törlése során!");
        }
    }
  
    public void trydeleteEmployee(int id) throws SQLException{
        System.out.println(id);
        Connection con = connectDb();
        String sql = "delete from employees where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.execute();
    }
   
    }
