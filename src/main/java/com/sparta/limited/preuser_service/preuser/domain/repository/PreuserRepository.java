package com.sparta.limited.preuser_service.preuser.domain.repository;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PreuserRepository {

    Preuser save(Preuser preuser);

    Preuser findById(UUID preuserId);

    Page<Preuser> findAll(Pageable pageable);

    Preuser findWithPessimisticLockById(UUID preuserId);

    List<Preuser> findByRecruitEndAtBeforeAndPreuserStatus(LocalDateTime now, PreuserStatus preuserStatus);

    List<Preuser> findByRecruitStartAtBeforeAndPreuserStatus(LocalDateTime now, PreuserStatus preuserStatus);
}
