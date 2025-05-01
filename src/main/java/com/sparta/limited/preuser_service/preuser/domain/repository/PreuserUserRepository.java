package com.sparta.limited.preuser_service.preuser.domain.repository;

import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;

import java.util.List;
import java.util.UUID;

public interface PreuserUserRepository {
    boolean existsByPreuserIdAndUserId(UUID preuserId, Long userId);

    void save(PreuserUser preuserUser);

    List<PreuserUser> findByPreuserId(UUID preuserId);

    void saveAll(List<PreuserUser> selectedUsers);

    void existsByPreuserIdAndIsSelectedTrue(UUID preuserId);
}
