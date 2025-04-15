package com.sparta.limited.preuser_service.preuser.domain.repository;

import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;

import java.util.UUID;

public interface PreuserUserRepository {
    void existsByPreuserIdAndUserId(UUID preuserId, Long userId);

    void save(PreuserUser preuserUser);
}
