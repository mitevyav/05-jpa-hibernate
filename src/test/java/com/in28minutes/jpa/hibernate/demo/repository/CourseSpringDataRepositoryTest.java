package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
public class CourseSpringDataRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById() {
        Optional<Course> courseOptional = repository.findById(10001L);
        if (courseOptional.isPresent()) {
            logger.info("Course: {}", courseOptional.get());
        } else {
            logger.info("Course not found");
        }
    }


    @Test
    public void doSomeStuff() {
//        Course course = new Course("Microservices in 100 Steps");
//        repository.save(course);
//
//        course.setName("Microservices in 100 Steps - Updated");
//        repository.save(course);

        logger.info("Courses -> {}", repository.findAll());
        logger.info("Count -> {}", repository.count());
    }


    @Test
    public void sort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        logger.info("Courses -> {}", repository.findAll(sort));
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());
        Pageable nextPageRequest =  firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(nextPageRequest);
        logger.info("Second Page -> {}", secondPage.getContent());
    }

    @Test
    public void findByName() {
        logger.info("Course by name -> {}", repository.findByName("Lambdas in 100 Steps"));
    }

    @Test
    public void usingNativeQuery() {
        logger.info("Courses -> {}", repository.coursesWith100StepsInNameNative());
    }
}
