package com.sparta.limited.preuser_service.preuser.domain.repository;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PreuserRepository {

    Preuser save(Preuser preuser);

    Preuser findById(UUID preuserId);

    Page<Preuser> findAll(Pageable pageable);
}
