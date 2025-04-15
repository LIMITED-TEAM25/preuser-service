package com.sparta.limited.preuser_service.preuser.infrastructure.persistence;


import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPreuserUserRepository extends JpaRepository<PreuserUser, UUID> {

    boolean existsByPreuserIdAndUserId(UUID preuserId, Long userId);
}
