package avatar.service.impl;

import avatar.entity.Avatar;
import avatar.repository.QueryAvatarRepository;
import avatar.service.QueryAvatarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class QueryAvatarImpl implements QueryAvatarService {
    @Autowired
    private QueryAvatarRepository queryAvatarRepository;

    @Override
    @Transactional(readOnly = true)
    public Avatar findAvatarById(String id) {
        return queryAvatarRepository.findById(id).orElse(null);
    }
}
