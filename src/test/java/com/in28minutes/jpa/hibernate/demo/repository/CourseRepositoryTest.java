package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext
    void deleteById_basic() {
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
//    @DirtiesContext
    void save_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        course.setName("JPA in 50 Steps - Updated");
        repository.save(course);

        Course courseAfterUpdate = repository.findById(10001L);
        assertEquals("JPA in 50 Steps - Updated", courseAfterUpdate.getName());
    }

    @Test
    @DirtiesContext
    void playWithEntityManager() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    void retrieveReviewsForCourse() {
        var course = repository.findById(10003L);
        logger.info("Reviews -> {}", course.getReviews());
    }

    @Test
    void retrieveCourseForAReview() {
        var review = entityManager.find(Review.class, 50001L);
        logger.info("Course from review -> {}", review.getCourse());
    }


    @Test
    @Transactional
    void retrieveCourseForAReviewCrud() {
        var review = reviewRepository.findById(50001L).get();
        logger.info("Course from review -> {}", review.getCourse().getReviews());
    }
}