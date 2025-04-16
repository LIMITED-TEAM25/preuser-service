package com.sparta.limited.preuser_service.preuser.infrastructure.persistence;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaPreuserRepository extends JpaRepository<Preuser, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Preuser> findWithPessimisticLockById(UUID preuserId);

    List<Preuser> findByRecruitEndAtBeforeAndPreuserStatus(LocalDateTime now, PreuserStatus preuserStatus);

    List<Preuser> findByRecruitStartAtBeforeAndPreuserStatus(LocalDateTime now, PreuserStatus preuserStatus);
}
