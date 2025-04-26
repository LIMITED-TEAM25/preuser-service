package com.sparta.limited.preuser_service.preuser.domain.status;

import java.time.LocalDateTime;

public enum PreuserStatus {
    RECRUITING("모집중"),
    CLOSED("종료"),
    PLANNED("예정"),
    ;
    private final String description;

    PreuserStatus(String description) {
        this.description = description;
    }

    public static PreuserStatus fromRecruitStartAt(LocalDateTime recruitStartAt) {
        return recruitStartAt.isAfter(LocalDateTime.now()) ? PLANNED : RECRUITING;
    }

    public String getDescription() {
        return description;
    }
}
