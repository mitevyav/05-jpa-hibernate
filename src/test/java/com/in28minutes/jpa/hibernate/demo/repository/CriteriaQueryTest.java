package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void all_courses() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> root = criteriaQuery.from(Course.class);

        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(root));
        List<Course> resultList = query.getResultList();
        logger.info("Select course with criteria query -> {}", resultList);
    }

    @Test
    void all_courses_having_100_steps() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> root = criteriaQuery.from(Course.class);

        Predicate like = criteriaBuilder.like(root.get("name"), "%100 Steps");
        criteriaQuery.where(like);

        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(root));
        List<Course> resultList = query.getResultList();
        logger.info("Select course with criteria query where course like %100 Steps -> {}", resultList);
    }


    @Test
    void all_courses_without_student() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> root = criteriaQuery.from(Course.class);

        Predicate studentsIsEmpty = criteriaBuilder.isEmpty(root.get("students"));
        criteriaQuery.where(studentsIsEmpty);

        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(root));
        List<Course> resultList = query.getResultList();
        logger.info("Select course with criteria query where course has no students -> {}", resultList);
    }


    @Test
    void join() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> root = criteriaQuery.from(Course.class);

        Join<Object, Object> join = root.join("students");

        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(root));
        List<Course> resultList = query.getResultList();
        logger.info("Select course with criteria query where course has no students -> {}", resultList);
    }

    @Test
    void left_join() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        Root<Course> root = criteriaQuery.from(Course.class);

        Join<Object, Object> join = root.join("students", JoinType.LEFT);

        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(root));
        List<Course> resultList = query.getResultList();
        logger.info("Select course with criteria query where course has no students -> {}", resultList);
    }


}
