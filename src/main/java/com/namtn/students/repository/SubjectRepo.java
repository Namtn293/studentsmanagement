package com.namtn.students.repository;

import com.namtn.students.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject,Long> {
}
