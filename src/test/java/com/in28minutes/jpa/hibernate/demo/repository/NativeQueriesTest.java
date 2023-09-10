package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void native_queries_basic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM course_details", Course.class);
        var resultList = query.getResultList();
        logger.info("Native query all courses -> {}", resultList);
    }


    @Test
    void native_queries_with_parameters() {
        Query query = entityManager.createNativeQuery("SELECT * FROM course_details WHERE id=?", Course.class);
        query.setParameter(1, 10001L);
        var resultList = query.getResultList();
        logger.info("Native query single courses -> {}", resultList);
    }

    @Test
    void native_queries_with_named_parameters() {
        Query query = entityManager.createNativeQuery("SELECT * FROM course_details WHERE id=:id", Course.class);
        query.setParameter("id", 10001L);
        var resultList = query.getResultList();
        logger.info("Native query single courses with named params -> {}", resultList);
    }


}