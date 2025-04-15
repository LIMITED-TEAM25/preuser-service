package com.sparta.limited.preuser_service.preuser.infrastructure.persistence;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import java.util.UUID;

public interface JpaPreuserRepository extends JpaRepository<Preuser, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Preuser> findWithPessimisticLockById(UUID preuserId);
}
