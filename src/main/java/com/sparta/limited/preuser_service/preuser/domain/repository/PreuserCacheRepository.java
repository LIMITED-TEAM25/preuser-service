package com.sparta.limited.preuser_service.preuser.domain.repository;

import com.sparta.limited.preuser_service.preuser.infrastructure.dto.response.UserSearchUserIdResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PreuserCacheRepository {

    void existsApplyUserId(UUID preuserId, Long userId);

    UserSearchUserIdResponse findUserInfo(Long userId);

    void addUserToPreuser(UUID preuserId, Long userId, LocalDateTime preuserEndAt);


    void saveUserInfo(Long userId, UserSearchUserIdResponse response, LocalDateTime preuserEndAt);
}
