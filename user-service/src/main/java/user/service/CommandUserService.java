package user.service;

import user.entity.User;

public interface CommandUserService {
    User save(User user);

    User update(User user);

    void delete(User user);
}
