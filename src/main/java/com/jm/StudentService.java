package com.jm;

import com.jm.model.Student;
import com.jm.repo.StudentRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StudentService {
    @Inject
    private StudentRepo studentRepo;

    public void add(Student student) {
        studentRepo.persist(student);
    }

    public List<Student> findAll() {
        return (List<Student>) studentRepo.findAll();
    }
}
