public class Employee {

    String name, department;
    int salary;

    public Employee(String name, String department, int salary) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public boolean match(Employee employee){
        return this.name.equals(employee.name) && this.department.equals(employee.department) && this.salary == employee.salary;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
