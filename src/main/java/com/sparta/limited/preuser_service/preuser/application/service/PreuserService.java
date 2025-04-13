package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserCreateResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserGetResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserUpdateStatusResponse;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import java.util.UUID;

public interface PreuserService {

    PreuserCreateResponse createPreuser(PreuserCreateRequest request);

    PreuserGetResponse getPreuser(UUID preuserId);

    PreuserUpdateStatusResponse updatePreuserStatus(UUID preuserId,
        PreuserStatus preuserStatus);
}
