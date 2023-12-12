package com.jm;

import com.jm.config.Traced;
import com.jm.model.Student;
import com.jm.repo.StudentRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StudentService {
    @Inject
    private StudentRepo studentRepo;

    @Traced
    public void add(Student student) {
        studentRepo.persist(student);
    }

    @Traced
    public List<Student> findAll() {
        return (List<Student>) studentRepo.findAll();
    }
}
