package views;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Database;
import models.Employee;

public class MainFrame  extends JFrame {

    DefaultTableModel model;
    JTable  table;
    JScrollPane pane;
    Database db;
    JPanel buttonPanel;
    JButton addbutton; 
    JButton delbutton;
    JButton editbutton;

 
    public MainFrame() {
        this.initComponent();
        this.SetComponent();
        this.setFrame();
       
    }
    private void initComponent(){
        this.model = new DefaultTableModel();
        this.table = new JTable(model);
        this.pane = new JScrollPane(this.table);
        this.db = new Database();
        this.buttonPanel = new JPanel();
        this.addbutton = new JButton("Hozzáad");
        this.delbutton = new JButton("Töröl");
        this.editbutton = new JButton("Szerkeszt");
    }
    private void SetComponent() {
        Object[] labels = {"Az",
        "Név",
        "Település",
         "Fizetés"
        };
         
         this.model.setColumnIdentifiers(labels);
         this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.LINE_AXIS));
         this.buttonPanel.add(this.addbutton);
         this.buttonPanel.add(this.delbutton);
         this.buttonPanel.add(this.editbutton);
        
        ArrayList<Employee> emplist = this.db.getEmployees();
        for(Employee emp: emplist){
            Vector<String> empStr = new Vector<>();
            empStr.add(emp.getId().toString());
            empStr.add(emp.getName());
            empStr.add(emp.getCity());
            empStr.add(emp.getSalary().toString());
           this.model.addRow(empStr);

        }

      
    }
    private void setFrame(){
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.add(this.pane);
        this.add(this.buttonPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setVisible(true);
    }
    
    public JButton getAddbutton() {
        return addbutton;
    }
    public JButton getDelbutton() {
        return delbutton;
    }
    public JButton getEditbutton() {
        return editbutton;
    }
    public JTable getTable() {
        return  this.table;
    }
    public DefaultTableModel getModel() {
        return this.model;
    }
    
}
