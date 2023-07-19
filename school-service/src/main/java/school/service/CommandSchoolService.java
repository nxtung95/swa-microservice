package school.service;

import school.entity.School;

public interface CommandSchoolService {
    School save(School school);

    School update(School school);

    void delete(School school);
}
