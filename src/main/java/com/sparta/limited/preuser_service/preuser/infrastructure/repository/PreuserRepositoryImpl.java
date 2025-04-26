package com.sparta.limited.preuser_service.preuser.infrastructure.repository;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import com.sparta.limited.preuser_service.preuser.infrastructure.persistence.JpaPreuserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PreuserRepositoryImpl implements PreuserRepository {

    private final JpaPreuserRepository jpaPreuserRepository;

    @Override
    public Preuser save(Preuser preuser) {
        return jpaPreuserRepository.save(preuser);
    }

    @Override
    public Preuser findById(UUID preuserId) {
        return jpaPreuserRepository.findById(preuserId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCES_NOT_FOUND));
    }

    @Override
    public Page<Preuser> findAll(Pageable pageable) {
        return jpaPreuserRepository.findAll(pageable);
    }

    @Override
    public Preuser findWithPessimisticLockById(UUID preuserId) {
        return jpaPreuserRepository.findWithPessimisticLockById(preuserId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCES_NOT_FOUND));
    }

    @Override
    public List<Preuser> findByRecruitEndAtBeforeAndPreuserStatus(LocalDateTime now, PreuserStatus preuserStatus) {
        return jpaPreuserRepository.findByRecruitEndAtBeforeAndPreuserStatus(now, preuserStatus);
    }

    @Override
    public List<Preuser> findByRecruitStartAtBeforeAndPreuserStatus(LocalDateTime now, PreuserStatus preuserStatus) {
        return jpaPreuserRepository.findByRecruitStartAtBeforeAndPreuserStatus(now, preuserStatus);
    }
}
