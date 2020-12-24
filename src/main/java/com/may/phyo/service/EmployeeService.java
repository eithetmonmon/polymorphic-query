package com.may.phyo.service;


import com.may.phyo.entity.Employee;
import com.may.phyo.entity.Manager;

import javax.persistence.EntityManager;

public class EmployeeService {

    private EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }
    public void createEmployee(Employee employee){
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }
    public void createManager(Manager manager){
        em.getTransaction().begin();
        em.persist(manager);
        em.getTransaction().commit();
    }
}
