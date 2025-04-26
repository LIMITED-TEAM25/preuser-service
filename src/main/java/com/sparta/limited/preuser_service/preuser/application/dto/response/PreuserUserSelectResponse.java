package com.sparta.limited.preuser_service.preuser.application.dto.response;

import com.sparta.limited.preuser_service.preuser.infrastructure.dto.response.UserSearchUserIdResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class PreuserUserSelectResponse {
    private UUID preuserId;
    private int preuserCount;
    private List<UserSearchUserIdResponse> preuserUserList = new ArrayList<>();

    private PreuserUserSelectResponse(
            UUID preuserId,
            int preuserCount,
            List<UserSearchUserIdResponse> preuserUserList
    ) {
        this.preuserId = preuserId;
        this.preuserCount = preuserCount;
        this.preuserUserList = preuserUserList;
    }

    public static PreuserUserSelectResponse of(
            UUID preuserId,
            int preuserCount,
            List<UserSearchUserIdResponse> preuserUserList
    ) {
        return new PreuserUserSelectResponse(preuserId, preuserCount, preuserUserList);
    }

}
