package teacher.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teacher.entity.Teacher;
import teacher.repository.CommandTeacherRepository;
import teacher.service.CommandTeacherService;

@Service
@Slf4j
public class CommandTeacherImpl implements CommandTeacherService {
    @Autowired
    private CommandTeacherRepository commandTeacherRepository;

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return commandTeacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public Teacher update(Teacher teacher) {
        return commandTeacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void delete(Teacher teacher) {
        commandTeacherRepository.delete(teacher);
    }
}
