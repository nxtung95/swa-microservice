package student.service;

import student.entity.Student;

public interface QueryStudentService {
    Student findStudentById(String id);
}
