package com.sparta.limited.preuser_service.preuser.application.mapper;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.*;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;

public class PreuserMapper {

    public static PreuserCreateResponse toPreuserCreateResponse(Preuser preuser) {
        return PreuserCreateResponse.of(preuser.getId());
    }

    public static PreuserUpdateStatusResponse toPreuserUpdateStatusResponse(Preuser preuser) {
        return PreuserUpdateStatusResponse.of(
                preuser.getId(),
                preuser.getPreuserTitle(),
                preuser.getPreuserStatus()
        );
    }


    public static PreuserEventApplyResponse toPreuserEventApplyResponse(Preuser preuser) {
        return PreuserEventApplyResponse.of(
                preuser.getId(),
                preuser.getPreuserTitle(),
                "신청이 완료 되었습니다"
        );
    }

    public static PreuserGetForPageResponse toPreuserGetForPageResponse(Preuser preuser) {
        return PreuserGetForPageResponse.of(
                preuser.getId(),
                preuser.getPreuserTitle()
        );
    }

    public static PreuserGetResponse toPreuserGetResponse(Preuser preuser) {
        return PreuserGetResponse.of(
                preuser.getId(),
                preuser.getPreuserTitle(),
                preuser.getPreuserCount(),
                preuser.getGenderLimit(),
                preuser.getAgeLimit(),
                preuser.getPreuserProductId(),
                preuser.getRecruitStartAt(),
                preuser.getRecruitEndAt(),
                preuser.getPreuserStartAt(),
                preuser.getPreuserEndAt(),
                preuser.getPreuserStatus(),
                preuser.getAnnounceDate()
        );
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
