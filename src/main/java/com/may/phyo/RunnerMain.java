package com.may.phyo;

import com.may.phyo.entity.Employee;
import com.may.phyo.entity.Manager;
import com.may.phyo.service.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RunnerMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("polymorphic_demo");
        EntityManager em = emf.createEntityManager();
        EmployeeService employeeService = new EmployeeService(em);
        employeeService.createEmployee(new Employee("Bill Cliton",5000));
        employeeService.createEmployee(new Employee("Angela",4000));
        employeeService.createEmployee(new Employee("Annie Song",2000));
        employeeService.createEmployee(new Employee("Charse",3000));

        employeeService.createManager(new Manager("Billy",4500,"Design"));
        employeeService.createManager(new Manager("Nicky",5000,"Marketing"));
        employeeService.createManager(new Manager("Kian",8000,"HR and Admin"));

        com.may.phyo.util.JPAUtil.checkData("select * from employee");
        com.may.phyo.util.JPAUtil.checkData("select * from manager");

        String queryString = "select emp from Employee emp where emp.salary > (select avg(e.salary) from Employee e)";
        TypedQuery<Employee> query = em.createQuery(queryString,Employee.class);
        List<Employee> employees = query.getResultList();
        displayQueryResult(queryString,employees);

        em.close();
        emf.close();

    }
    public static void displayQueryResult(String jpql , List<Employee> employees){
        System.out.println("\n----------Query of Result \""+jpql + "\" ");
        for (Employee employee : employees){
            System.out.println("Employee : "+employee.getId()+","+
            employee.getName()+","+
                    employee.getSalary()
            );
        }
    }
}
