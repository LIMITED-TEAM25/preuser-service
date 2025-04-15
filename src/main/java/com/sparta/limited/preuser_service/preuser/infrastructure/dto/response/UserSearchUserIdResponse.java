package com.sparta.limited.preuser_service.preuser.infrastructure.dto.response;

import com.sparta.limited.preuser_service.preuser.domain.limit.GenderLimit;
import lombok.Getter;

@Getter
public class UserSearchUserIdResponse {
    private final Long userId;
    private final String username;
    private final GenderLimit gender;
    private final Integer age;


    private UserSearchUserIdResponse(
            Long userId,
            String username,
            GenderLimit gender,
            Integer age
    ) {
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.age = age;
    }


}
