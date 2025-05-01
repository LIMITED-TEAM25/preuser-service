package com.sparta.limited.preuser_service.preuser.application.preuserSelector;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;
import com.sparta.limited.preuser_service.preuser.infrastructure.constants.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class PreuserSelectorFromCache {

    private final StringRedisTemplate stringRedisTemplate;

    public  PreuserSelectorFromCache(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public List<PreuserUser> selectPreuser(UUID preuserId, List<PreuserUser> preuserUserList, int selectCount) {
        String key = RedisKey.PREUSER;
        try {
            Set<String> selectUserIds = stringRedisTemplate.opsForSet()
                    .distinctRandomMembers(key + preuserId, selectCount);

            if (selectUserIds.isEmpty()) {
                throw new BusinessException(ErrorCode.RESOURCES_NOT_FOUND, "Redis 실패");
            }

            Map<String, PreuserUser> preuserMap = new HashMap<>();
            for (PreuserUser user : preuserUserList) {
                preuserMap.put(String.valueOf(user.getUserId()), user);
            }

            List<PreuserUser> selectUsers = new ArrayList<>();
            for (String userId : selectUserIds) {
                PreuserUser user = preuserMap.get(userId);
                if (user != null) {
                    selectUsers.add(user);
                }
            }

            for (PreuserUser selectUser : selectUsers) {
                selectUser.select();
            }

            return selectUsers;

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.RESOURCES_NOT_FOUND, "Redis 에러");
        }
    }
}
