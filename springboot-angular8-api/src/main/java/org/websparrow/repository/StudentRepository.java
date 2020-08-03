package org.websparrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.websparrow.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
