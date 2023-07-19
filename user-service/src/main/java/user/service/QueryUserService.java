package user.service;

import user.entity.User;

public interface QueryUserService {
    User findUserById(String id);
}
