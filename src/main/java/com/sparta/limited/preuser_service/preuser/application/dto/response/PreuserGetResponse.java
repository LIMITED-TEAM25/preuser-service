package com.sparta.limited.preuser_service.preuser.application.dto.response;

import com.sparta.limited.preuser_service.preuser.domain.limit.GenderLimit;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PreuserGetResponse {

    private UUID preuserId;
    private String preuserTitle;
    private int preuserCount;
    private GenderLimit genderLimit;
    private int ageLimit;
    private UUID productId;
    private LocalDateTime recruitStartAt;
    private LocalDateTime recruitEndAt;
    private LocalDateTime preuserStartAt;
    private LocalDateTime preuserEndAt;
    private PreuserStatus preuserStatus;
    private LocalDateTime announceDate;

    private PreuserGetResponse(
        UUID preuserId,
        String preuserTitle,
        int preuserCount,
        GenderLimit genderLimit,
        int ageLimit,
        UUID productId,
        LocalDateTime recruitStartAt,
        LocalDateTime recruitEndAt,
        LocalDateTime preuserStartAt,
        LocalDateTime preuserEndAt,
        PreuserStatus preuserStatus,
        LocalDateTime announceDate
    ) {
        this.preuserId = preuserId;
        this.preuserTitle = preuserTitle;
        this.preuserCount = preuserCount;
        this.genderLimit = genderLimit;
        this.ageLimit = ageLimit;
        this.productId = productId;
        this.recruitStartAt = recruitStartAt;
        this.recruitEndAt = recruitEndAt;
        this.preuserStartAt = preuserStartAt;
        this.preuserEndAt = preuserEndAt;
        this.preuserStatus = preuserStatus;
        this.announceDate = announceDate;
    }

    public static PreuserGetResponse of(
        UUID preuserId,
        String preuserTitle,
        int preuserCount,
        GenderLimit genderLimit,
        int ageLimit,
        UUID productId,
        LocalDateTime recruitStartAt,
        LocalDateTime recruitEndAt,
        LocalDateTime preuserStartAt,
        LocalDateTime preuserEndAt,
        PreuserStatus preuserStatus,
        LocalDateTime announceDate
    ) {
        return new PreuserGetResponse(
            preuserId,
            preuserTitle,
            preuserCount,
            genderLimit,
            ageLimit,
            productId,
            recruitStartAt,
            recruitEndAt,
            preuserStartAt,
            preuserEndAt,
            preuserStatus,
            announceDate
        );
    }

}
