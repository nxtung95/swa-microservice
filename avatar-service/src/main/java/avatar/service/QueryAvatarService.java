package avatar.service;

import avatar.entity.Avatar;

public interface QueryAvatarService {
    Avatar findAvatarById(String id);
}
