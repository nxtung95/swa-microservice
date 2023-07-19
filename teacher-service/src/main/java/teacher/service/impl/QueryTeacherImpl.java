package teacher.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teacher.entity.Teacher;
import teacher.repository.QueryTeacherRepository;
import teacher.service.QueryTeacherService;

@Service
@Slf4j
public class QueryTeacherImpl implements QueryTeacherService {
    @Autowired
    private QueryTeacherRepository queryTeacherRepository;

    @Override
    @Transactional(readOnly = true)
    public Teacher findTeacherById(String id) {
        return queryTeacherRepository.findById(id).orElse(null);
    }
}
