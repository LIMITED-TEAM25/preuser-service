package com.sparta.limited.preuser_service.preuser.application.validate;

import java.time.LocalDateTime;

public class ApplyPreuserPeriodValidator {

    public static boolean validateApplyPeriod(LocalDateTime recruitStartDate, LocalDateTime recruitEndDate) {
        LocalDateTime now = LocalDateTime.now();

        return !now.isBefore(recruitStartDate) && !now.isAfter(recruitEndDate);
    }

}
