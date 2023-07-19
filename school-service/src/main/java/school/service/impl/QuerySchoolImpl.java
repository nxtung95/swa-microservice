package school.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.entity.School;
import school.repository.QuerySchoolRepository;
import school.service.QuerySchoolService;

@Service
@Slf4j
public class QuerySchoolImpl implements QuerySchoolService {
    @Autowired
    private QuerySchoolRepository querySchoolRepository;

    @Override
    @Transactional(readOnly = true)
    public School findSchoolById(String id) {
        return querySchoolRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public School findSchoolByEmail(String email) {
        return querySchoolRepository.findByEmail(email).orElse(null);
    }
}
