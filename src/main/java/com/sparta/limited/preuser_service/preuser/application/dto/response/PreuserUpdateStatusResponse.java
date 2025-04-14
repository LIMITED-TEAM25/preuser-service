package com.sparta.limited.preuser_service.preuser.application.dto.response;

import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PreuserUpdateStatusResponse {

    private UUID preuserId;
    private String preuserTitle;
    private PreuserStatus preuserStatus;

    private PreuserUpdateStatusResponse(
        UUID preuserId,
        String preuserTitle,
        PreuserStatus preuserStatus) {
        this.preuserId = preuserId;
        this.preuserStatus = preuserStatus;
        this.preuserTitle = preuserTitle;
    }

    public static PreuserUpdateStatusResponse of(
        UUID preuserId,
        String preuserTitle,
        PreuserStatus preuserStatus) {
        return new PreuserUpdateStatusResponse(
            preuserId,
            preuserTitle,
            preuserStatus);
    }
}
