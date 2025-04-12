package com.sparta.limited.preuser_service.preuser.application.dto.request;


import com.sparta.limited.preuser_service.preuser.domain.limit.GenderLimit;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PreuserCreateRequest {

    @NotBlank(message = "체험단 제목은 필수 입니다")
    private String preuserTitle;

    @Min(value = 1, message = "체험단 모집 인원은 1명 이상이어야 합니다")

    private int preuserCount;

    private GenderLimit genderLimit;

    private int ageLimit;

    @NotNull(message = "체험단 상품은 필수 입니다")
    private UUID preuserProductId;

    @NotNull(message = "체험단 모집 시작일은 필수 입니다")
    private LocalDateTime recruitStartAt;

    @NotNull(message = "체험단 모집 마감일은 필수 입니다")
    private LocalDateTime recruitEndAt;

    @NotNull(message = "체험단 시작일은 필수 입니다")
    private LocalDateTime preuserStartAt;

    @NotNull(message = "체험단 종료일은 필수 입니다")
    private LocalDateTime preuserEndAt;

    @NotNull(message = "체험단 상태는 필수입니다")
    private PreuserStatus preuserStatus;

    @NotNull(message = "체험단 발표일은 필수입니다")
    private LocalDateTime announceDate;


}
