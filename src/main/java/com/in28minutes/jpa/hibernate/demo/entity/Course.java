package com.in28minutes.jpa.hibernate.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CourseDetails")
//@NamedQuery(name = "query_get_all_courses", query = "Select c From Course c")
//@NamedQuery(name = "query_get_100_Steps_courses", query = "Select c From Course c where name like '%100 Steps'")
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
        @NamedQuery(name = "query_get_100_Steps_courses", query = "Select c From Course c where name like '%100 Steps'")
})
@SQLDelete(sql = "update course_details set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course")
//    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER) // default is lazy
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    private boolean isDeleted;

    @PreRemove
    private void preRemove() {
        isDeleted = true;
    }

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
