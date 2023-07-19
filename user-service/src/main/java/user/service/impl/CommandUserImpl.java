package user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.entity.User;
import user.repository.CommandUserRepository;
import user.service.CommandUserService;

@Service
@Slf4j
public class CommandUserImpl implements CommandUserService {
    @Autowired
    private CommandUserRepository commandUserRepository;

    @Override
    @Transactional
    public User save(User user) {
        return commandUserRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return commandUserRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        commandUserRepository.delete(user);
    }
}
