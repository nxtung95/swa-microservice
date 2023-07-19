package teacher.service;

import teacher.entity.Teacher;

public interface QueryTeacherService {
    Teacher findTeacherById(String id);
}
