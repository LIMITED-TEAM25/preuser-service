package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserCacheRepository;
import com.sparta.limited.preuser_service.preuser.infrastructure.client.UserClient;
import com.sparta.limited.preuser_service.preuser.infrastructure.dto.response.UserSearchUserIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PreuserCacheServiceImpl implements PreuserCacheService {

    private final PreuserCacheRepository preuserCacheRepository;
    private final UserClient userClient;

    @Async
    @Override
    public void cacheAllUsersForPreuser(LocalDateTime preuserEndAt, String userRole) {
        int page = 0;
        int size = 1000;
        boolean hasNext = true;

        try {
            while (hasNext) {
                Page<UserSearchUserIdResponse> response = userClient.getAllUsersForCache(page, size, userRole);
                List<UserSearchUserIdResponse> users = response.getContent();

                for (UserSearchUserIdResponse user : users) {
                    preuserCacheRepository.saveUserInfo(user.getUserId(), user, preuserEndAt);
                }


                hasNext = !response.isLast();
                page++;
            }

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER,"캐싱 실패");
        }
    }
}
