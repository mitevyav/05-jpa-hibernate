package com.in28minutes.jpa.hibernate.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "CourseDetails")
//@NamedQuery(name = "query_get_all_courses", query = "Select c From Course c")
//@NamedQuery(name = "query_get_100_Steps_courses", query = "Select c From Course c where name like '%100 Steps'")
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
        @NamedQuery(name = "query_get_100_Steps_courses", query = "Select c From Course c where name like '%100 Steps'")
})
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n\nCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
