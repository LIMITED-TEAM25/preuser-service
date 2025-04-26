package com.sparta.limited.preuser_service.preuser.infrastructure.client;

import com.sparta.limited.preuser_service.preuser.infrastructure.dto.response.UserSearchUserIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/v1/internal/users/{userId}")
    UserSearchUserIdResponse getUserById(@PathVariable("userId") Long userId);
}
