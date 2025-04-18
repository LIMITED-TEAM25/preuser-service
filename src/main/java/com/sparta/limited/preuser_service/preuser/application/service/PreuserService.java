package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.*;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PreuserService {

    PreuserCreateResponse createPreuser(PreuserCreateRequest request);

    PreuserGetResponse getPreuser(UUID preuserId);

    PreuserUpdateStatusResponse updatePreuserStatus(UUID preuserId,
                                                    PreuserStatus preuserStatus);

    Page<PreuserGetForPageResponse> getAllPreuesr(Pageable pageable);

    PreuserEventApplyResponse applyPreuserEvents(UUID preuserId, Long userId);

    PreuserUserSelectResponse selectPreuserUser(UUID preuesrId);
}
