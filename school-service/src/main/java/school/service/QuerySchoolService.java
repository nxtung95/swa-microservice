package school.service;

import school.entity.School;

public interface QuerySchoolService {
    School findSchoolById(String id);
}
