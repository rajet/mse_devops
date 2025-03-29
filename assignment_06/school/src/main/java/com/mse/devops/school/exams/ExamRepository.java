package com.mse.devops.school.exams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
