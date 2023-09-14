package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void jpql_basic() {
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }


    @Test
    void jpql_typed() {
        TypedQuery<Course> typedQuery = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void jpql_where() {
        TypedQuery<Course> typedQuery = entityManager.createNamedQuery("query_get_100_Steps_courses", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        logger.info("Select c From Course c where name like '%0 Steps' -> {}", resultList);
    }


    @Test
    void jpql_courses_without_students() {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    void jpql_courses_with_at_least_2_students() {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    void jpql_courses_ordered_students() {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    void jpql_students_with_passport_in_a_certain_pattern() {
        TypedQuery<Student> query = entityManager.createQuery("Select s from Student s where s.passport.number like '%98%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }


    @Test
    void join() {
        Query query = entityManager.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size -> {}", resultList.size());
        for (Object[] result : resultList) {
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    void left_join() {
        Query query = entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size -> {}", resultList.size());
        for (Object[] result : resultList) {
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }

    @Test
    void cross_join() {
        Query query = entityManager.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results size -> {}", resultList.size());
        for (Object[] result : resultList) {
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }
}