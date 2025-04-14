package com.sparta.limited.preuser_service.preuser.application.dto.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PreuserGetForPageResponse {

    private UUID preuserId;
    private String preuserTitle;

    private PreuserGetForPageResponse(UUID preuserId, String preuserTitle) {
        this.preuserId = preuserId;
        this.preuserTitle = preuserTitle;
    }

    public static PreuserGetForPageResponse of(
            UUID preuserId,
            String preuserTitle
    ) {
        return new PreuserGetForPageResponse(preuserId, preuserTitle);
    }
}
