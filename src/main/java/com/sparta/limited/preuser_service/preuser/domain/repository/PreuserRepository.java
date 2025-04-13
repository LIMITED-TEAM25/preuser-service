package com.sparta.limited.preuser_service.preuser.domain.repository;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import java.util.UUID;

public interface PreuserRepository {

    Preuser save(Preuser preuser);

    Preuser findById(UUID preuserId);
}
