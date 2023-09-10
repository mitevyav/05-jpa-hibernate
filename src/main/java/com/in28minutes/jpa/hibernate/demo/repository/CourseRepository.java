package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
//        logger.info("Play with EntityManager start...");
//
//        Course course1 = new Course("Web Services in 100 Steps");
//        course1.setName(null);
//        entityManager.persist(course1);
//        entityManager.flush();
//        Course course2 = new Course("AngularJS in 100 Steps");
//        entityManager.persist(course2);
//        entityManager.flush();
//
//
//
//        course1.setName("Web Services in 100 Steps - Updated");
//        course2.setName("AngularJS in 100 Steps - Updated");
//
//        entityManager.refresh(course1);
//        entityManager.flush();
    }


}



