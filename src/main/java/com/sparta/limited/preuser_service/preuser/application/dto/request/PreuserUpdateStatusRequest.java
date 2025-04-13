package com.sparta.limited.preuser_service.preuser.application.dto.request;

import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PreuserUpdateStatusRequest {

    @NotNull(message = "체험단 상태는 필수입니다")
    private PreuserStatus preuserStatus;

}
