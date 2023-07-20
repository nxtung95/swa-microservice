package student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.entity.Student;
import student.repository.QueryStudentRepository;
import student.service.QueryStudentService;

@Service
@Slf4j
public class QueryStudentImpl implements QueryStudentService {
    @Autowired
    private QueryStudentRepository queryStudentRepository;

    @Override
    @Transactional(readOnly = true)
    public Student findStudentById(String id) {
        return queryStudentRepository.findById(id).orElse(null);
    }
}
