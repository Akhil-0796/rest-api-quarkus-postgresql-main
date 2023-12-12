package com.jm.repo;

import com.jm.model.Student;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepo implements PanacheMongoRepository<Student> {
}
