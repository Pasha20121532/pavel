package com.Murashev.pavel.domain.repo;


import com.Murashev.pavel.domain.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepo extends CrudRepository<Student, Long> {
    List<Student> findByNamegroup(String namegroup);
}

