package com.sparta.limited.preuser_service.preuser.application.mapper;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserCreateResponse;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;

public class PreuserMapper {

    public static PreuserCreateResponse toPreuserCreateResponse(Preuser preuser) {
        return PreuserCreateResponse.of(preuser.getId());
    }

    public static Preuser toEntity(PreuserCreateRequest request) {
        return Preuser.of(
            request.getPreuserTitle(),
            request.getPreuserCount(),
            request.getGenderLimit(),
            request.getAgeLimit(),
            request.getPreuserProductId(),
            request.getRecruitStartAt(),
            request.getRecruitEndAt(),
            request.getPreuserStartAt(),
            request.getPreuserEndAt(),
            request.getPreuserStatus(),
            request.getAnnounceDate()
        );
    }
}
