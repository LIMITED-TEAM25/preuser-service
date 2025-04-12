package com.sparta.limited.preuser_service.preuser.domain.status;

public enum PreuserStatus {
    RECRUITING("모집중"),
    CLOSED("종료"),
    PLANNED("예정"),
    ;
    private final String description;

    PreuserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
