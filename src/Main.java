import BreezySwing.GBFrame;
import BreezySwing.IntegerField;

import javax.swing.*;
import java.util.ListIterator;

public class Main extends GBFrame {

    static DoublyLinkedList<Employee> list = new DoublyLinkedList<>();

    JButton addEmployeeButton = addButton("Add Employee", 2, 1, 1, 1);
    JTextField employeeName = addTextField("", 2, 2, 1, 1);
    JTextField employeeDept = addTextField("", 2, 3, 1, 1);
    IntegerField employeeSalary = addIntegerField(0, 2, 4, 1, 1);
    JButton remEmployeeButton = addButton("Remove Employee", 3, 1, 1, 1);
    JTextField remEmployeeName = addTextField("", 3, 2, 1, 1);
    JTextField remEmployeeDept = addTextField("", 3, 3, 1, 1);
    IntegerField remEmployeeSalary = addIntegerField(0, 3, 4, 1, 1);
    JButton sortEmployeesAlpha = addButton("Sort Employees by Name", 4, 1, 1, 1);
    JButton sortEmployeesSalary = addButton("Sort Employees by Salary", 5, 1, 1, 1);

    Main(){
        addLabel("DATA NEEDED:",1,1,1,1);
        addLabel("Name", 1, 2, 1, 1);
        addLabel("Department", 1, 3, 1, 1);
        addLabel("Salary", 1, 4, 1, 1);
    }

    public static void main(String[] args) {
        JFrame frm = new Main();
        frm.setTitle("Employee Organizer");
        frm.setSize(500, 150);
        frm.setVisible(true);
    }

    public void buttonClicked(JButton buttonObj) {
        if (buttonObj == addEmployeeButton) {
            Employee temp = new Employee(employeeName.getText(), employeeDept.getText(), employeeSalary.getNumber());
            list.add(temp);
        } else if (buttonObj == sortEmployeesAlpha) {
            sort(true);
            ListIterator<Employee> iterator = list.iterator();
            StringBuilder displayMessage = new StringBuilder();
            while (iterator.hasNext()) {
                displayMessage.append(iterator.next().toString()).append("\n");
            }
            messageBox(displayMessage.toString());
        } else if (buttonObj == sortEmployeesSalary) {
            sort(false);
            ListIterator<Employee> iterator = list.iterator();
            StringBuilder displayMessage = new StringBuilder();
            while (iterator.hasNext()) {
                displayMessage.append(iterator.next().toString()).append("\n");
            }
            messageBox(displayMessage.toString());
        } else if (buttonObj == remEmployeeButton) {
            boolean deleted = false;
            String name = remEmployeeName.getText();
            String dept = remEmployeeDept.getText();
            int salary = remEmployeeSalary.getNumber();
            Employee wantToDelete = new Employee(name, dept, salary);
            ListIterator<Employee> iterator = list.iterator();
            while (iterator.hasNext()){
                Employee employee = iterator.next();
                if (employee.match(wantToDelete)){
                    iterator.remove();
                    deleted = true;
                }
                if (deleted) break;
            }

            if (deleted) {
                messageBox("Deleted " + wantToDelete);
            } else {
                messageBox("Could not find " + wantToDelete);
            }
        }
    }

    public void sort(boolean name){
        DoublyLinkedList<Employee> sorted = new DoublyLinkedList<>();
        ListIterator<Employee> iterator = list.iterator();
        for(int i = 0; iterator.hasNext(); i++){
            ListIterator<Employee> sortedIterator = sorted.iterator();
            if (i == 0){
                sorted.add(iterator.next());
            } else{
                Employee next = iterator.next();
                boolean added = false;
                while(sortedIterator.hasNext()){
                    Employee employee = sortedIterator.next();
                    boolean toTest = name ? next.name.compareTo(employee.name) < 0 : next.salary < employee.salary;
                    if (toTest){
                        sortedIterator.previous();
                        sortedIterator.add(next);
                        added = true;
                    }
                    if (added) break;
                }
                if (!added) sortedIterator.add(next);
            }
        }
        list = sorted;
    }
}
