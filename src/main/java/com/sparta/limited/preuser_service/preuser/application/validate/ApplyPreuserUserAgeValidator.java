package com.sparta.limited.preuser_service.preuser.application.validate;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;

public class ApplyPreuserUserAgeValidator {

    private ApplyPreuserUserAgeValidator() {
    }

    public static void validate(int ageLimit, int userAge) {
        if (ageLimit == 0) return; // 제한 없음

        if (userAge < ageLimit) {
            throw new BusinessException(ErrorCode.UNMATCHED_USER_DATA, "신청 가능한 나이가 아닙니다");
        }
    }
}
