package com.sparta.limited.preuser_service.preuser.application.validate;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;

import java.time.LocalDateTime;

public class ApplyPreuserPeriodValidator {

    private ApplyPreuserPeriodValidator() {
    }

    public static void validate(LocalDateTime recruitStartAt, LocalDateTime recruitEndAt) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(recruitStartAt) || now.isAfter(recruitEndAt)) {
            throw new BusinessException(ErrorCode.UNMATCHED_USER_DATA, "신청기간이 아닙니다");
        }
    }

}
