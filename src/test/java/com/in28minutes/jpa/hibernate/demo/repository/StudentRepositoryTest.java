package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Address;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    void retrieveStudentAndPassportDetails() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        Passport passport = student.getPassport();
        logger.info("Passport -> {}", passport);
    }

    @Test
    @Transactional
    void setAddressDetails() {
        Student student = entityManager.find(Student.class, 20001L);
        student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
        entityManager.flush();
        logger.info("Student -> {}", student);
        Passport passport = student.getPassport();
        logger.info("Passport -> {}", passport);
    }

    @Test
    @Transactional
    void retrievePassportAndAssociatedStudent() {
        Passport passport = entityManager.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        Student student = passport.getStudent();
        logger.info("Student -> {}", student);
    }

    @Test
    @Transactional
    void retrieveStudentAndCourses() {
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("student.getCourses() -> {}", student);
        logger.info("student.getCourses() -> {}", student);
    }
}
