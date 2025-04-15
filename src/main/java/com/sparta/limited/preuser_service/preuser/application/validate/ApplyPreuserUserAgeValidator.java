package com.sparta.limited.preuser_service.preuser.application.validate;

public class ApplyPreuserUserAgeValidator {

    public static boolean validateAgeLimit(int ageLimit, int userAge) {
        if (ageLimit == 0) {
            return true;
        }
        return userAge >= ageLimit;
    }
}
