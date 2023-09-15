package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    List<Course> findByNameAndId(String name, Long id);

    List<Course> countByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

    @Query("Select c From Course c where name like '%100 Steps'")
    List<Course> coursesWith100StepsInName();

    @Query(value = "Select * From course_details where full_name like '%100 Steps'", nativeQuery = true)
    List<Course> coursesWith100StepsInNameNative();

    @Query(name = "query_get_100_Steps_courses")
    List<Course> coursesWith100StepsInNameNamed();
}
