package school.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.entity.School;
import school.repository.CommandSchoolRepository;
import school.service.CommandSchoolService;

@Service
@Slf4j
public class CommandSchoolImpl implements CommandSchoolService {
    @Autowired
    private CommandSchoolRepository commandSchoolRepository;

    @Override
    @Transactional
    public School save(School school) {
        return commandSchoolRepository.save(school);
    }

    @Override
    @Transactional
    public School update(School school) {
        return commandSchoolRepository.save(school);
    }

    @Override
    @Transactional
    public void delete(School school) {
        commandSchoolRepository.delete(school);
    }
}
