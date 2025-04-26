package com.sparta.limited.preuser_service.preuser.application.validate;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.limit.GenderLimit;

public class ApplyPreuserUserGenderValidator {

    private ApplyPreuserUserGenderValidator() {
    }

    public static void validate(GenderLimit limit, GenderLimit userGender) {
        if (limit == null) return;

        if (limit != userGender) {
            throw new BusinessException(ErrorCode.UNMATCHED_USER_DATA, "신청 가능한 성별이 아닙니다");
        }
    }
}
