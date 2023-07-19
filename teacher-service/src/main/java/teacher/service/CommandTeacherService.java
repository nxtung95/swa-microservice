package teacher.service;

import teacher.entity.Teacher;

public interface CommandTeacherService {
    Teacher save(Teacher teacher);

    Teacher update(Teacher teacher);

    void delete(Teacher teacher);
}
