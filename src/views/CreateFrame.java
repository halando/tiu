package views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;

import models.Employee;

public class CreateFrame extends JDialog{
    InputPanel idPanel;
    InputPanel namePanel;
    InputPanel cityPanel;
    InputPanel  salaryPanel;
    JPanel buttonPanel;
    JButton addButton;
    MainFrame mainFrame;
   
    public CreateFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.idPanel = new InputPanel("Azonító");
        this.namePanel = new InputPanel("Név");
        this.cityPanel = new InputPanel("Település");
        this.salaryPanel = new InputPanel("Fizetés");
        this.buttonPanel = new JPanel();
        this.addButton = new JButton("Mentés");

        this.buttonPanel.add(this.addButton);

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        // this.setLayout(new GridLayout(4,2));
        this.add(this.idPanel);
        this.add(this.namePanel);
        this.add(this.cityPanel);
        this.add(this.salaryPanel);
        this.add(this.buttonPanel);
        //this.setSize(400,300);
       // this.pack();
        this.setSize(250,150);
        this.setModal(true);
        this.setLocationRelativeTo(this.mainFrame);

}
        public void setEmployee(Employee emp){
            System.out.println("id setEmployeeban:" + emp.getId());
            this.idPanel.setValue(emp.getId().toString());
            this.namePanel.setValue(emp.getName());
            this.cityPanel.setValue(emp.getCity());
            this.salaryPanel.setValue(emp.getSalary().toString());
        }
    public JButton getAddButton() {
        return addButton;
    }
    public InputPanel getIdPanel() {
        return idPanel;
    }
    public InputPanel getNamePanel() {
        return namePanel;
    }
    public InputPanel getCityPanel() {
        return cityPanel;
    }
    public InputPanel getSalaryPanel() {
        return salaryPanel;
    }
    public JPanel getButtonPanel() {
        return buttonPanel;
    }
    
}
