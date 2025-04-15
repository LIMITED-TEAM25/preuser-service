package com.sparta.limited.preuser_service.preuser.application.validate;

import com.sparta.limited.preuser_service.preuser.domain.limit.GenderLimit;

public class ApplyPreuserUserGenderValidator {

    public static boolean validateUserGenderLimit(GenderLimit limit, GenderLimit userGender) {
        if (limit == null) {
            return true;
        }
        return limit == userGender;
    }

}
