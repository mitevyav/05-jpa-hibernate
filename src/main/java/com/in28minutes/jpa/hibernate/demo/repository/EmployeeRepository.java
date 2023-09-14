package com.in28minutes.jpa.hibernate.demo.repository;


import com.in28minutes.jpa.hibernate.demo.entity.Employee;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public void insert(Employee employee) {
        if (employee.getId() == null) {
            entityManager.persist(employee);
        } else {
            entityManager.merge(employee);
        }
    }

    public void deleteById(Long id) {
        Employee employee = findById(id);
        entityManager.remove(employee);
    }

//    public List<Employee> retrieveAllEmployees() {
//        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
//    }

    public List<Employee> retrieveAllFullTimeEmployees() {
        return entityManager.createQuery("select e from PartTimeEmployee e", Employee.class).getResultList();
    }

    public List<Employee> retrieveAllPartTimeEmployees() {
        return entityManager.createQuery("select e from FullTimeEmployee e", Employee.class).getResultList();
    }
}
