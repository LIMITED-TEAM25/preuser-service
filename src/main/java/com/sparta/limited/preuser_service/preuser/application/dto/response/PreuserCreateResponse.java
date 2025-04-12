package com.sparta.limited.preuser_service.preuser.application.dto.response;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PreuserCreateResponse {

    private final UUID id;

    private PreuserCreateResponse(UUID id) {
        this.id = id;
    }

    public static PreuserCreateResponse of(UUID id) {
        return new PreuserCreateResponse(id);
    }
}
