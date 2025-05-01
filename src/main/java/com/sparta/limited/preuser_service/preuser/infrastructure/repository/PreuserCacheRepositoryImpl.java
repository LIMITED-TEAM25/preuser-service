package com.sparta.limited.preuser_service.preuser.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserCacheRepository;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserUserRepository;
import com.sparta.limited.preuser_service.preuser.infrastructure.client.UserClient;
import com.sparta.limited.preuser_service.preuser.infrastructure.constants.RedisKey;
import com.sparta.limited.preuser_service.preuser.infrastructure.dto.response.UserSearchUserIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PreuserCacheRepositoryImpl implements PreuserCacheRepository {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;
    private final PreuserUserRepository preuserUserRepository;
    private final UserClient userClient;

    @Override
    public void existsApplyUserId(UUID preuserId, Long userId) {
        String key = RedisKey.PREUSER + preuserId;
        try {
            Boolean isApplied = stringRedisTemplate.opsForSet().isMember(key, userId.toString());
            if (Boolean.TRUE.equals(isApplied)) {
                throw new BusinessException(ErrorCode.DUPLICATE_RESOURCE, "이미 신청한 사용자 입니다");
            }
        } catch (Exception e) {
            if (preuserUserRepository.existsByPreuserIdAndUserId(preuserId, userId)) {
                throw new BusinessException(ErrorCode.DUPLICATE_RESOURCE, "이미 신청한 사용자 입니다");
            }
        }
    }

    @Override
    public UserSearchUserIdResponse findUserInfo(Long userId) {
        String key = RedisKey.USER + userId;
        try {
            String cached = stringRedisTemplate.opsForValue().get(key);
            if (cached != null && !cached.isBlank()) {
                return objectMapper.readValue(cached, UserSearchUserIdResponse.class);
            }
        } catch (Exception ignored) {}

        UserSearchUserIdResponse response = userClient.getUserById(userId);
        return response;
    }

    @Override
    public void addUserToPreuser(UUID preuserId, Long userId, LocalDateTime preuserEndAt) {
        String key = RedisKey.PREUSER + preuserId;

        try {
            stringRedisTemplate.opsForSet().add(key, userId.toString());

            Duration ttl = Duration.between(LocalDateTime.now(), preuserEndAt);
            if (!ttl.isNegative() && !ttl.isZero()) {
                stringRedisTemplate.expire(key, ttl);
            }
        } catch (Exception ignored) {
        }
    }


    @Override
    public void saveUserInfo(Long userId, UserSearchUserIdResponse response, LocalDateTime preuserEndAt) {
        String key = RedisKey.USER + userId;
        try {
            Duration ttl = Duration.between(LocalDateTime.now(), preuserEndAt);
            if (!ttl.isNegative() && !ttl.isZero()) {
                String value = objectMapper.writeValueAsString(response);
                stringRedisTemplate.opsForValue().set(key, value, ttl);
            }
        } catch (Exception ignored) {}
    }
}