package user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.entity.User;
import user.repository.QueryUserRepository;
import user.service.QueryUserService;

@Service
@Slf4j
public class QueryUserImpl implements QueryUserService {
    @Autowired
    private QueryUserRepository queryUserRepository;

    @Override
    @Transactional(readOnly = true)
    public User findUserById(String id) {
        return queryUserRepository.findById(id).orElse(null);
    }
}
