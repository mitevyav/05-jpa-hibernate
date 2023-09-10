package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
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
}