package com.sparta.limited.preuser_service.preuser.application.dto.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PreuserEventApplyResponse {

    private UUID preuserId;
    private String preuserTitle;
    private String message;

    private PreuserEventApplyResponse(
            UUID preuserId,
            String preuserTitle,
            String message
    ) {
        this.preuserId = preuserId;
        this.preuserTitle = preuserTitle;
        this.message = message;
    }

    public static PreuserEventApplyResponse of(
            UUID preuserId,
            String preuserTitle,
            String message
    ) {
        return new PreuserEventApplyResponse(preuserId, preuserTitle, message);
    }
}
