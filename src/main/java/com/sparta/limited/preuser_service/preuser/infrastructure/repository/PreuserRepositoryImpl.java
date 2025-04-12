package com.sparta.limited.preuser_service.preuser.infrastructure.repository;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import com.sparta.limited.preuser_service.preuser.infrastructure.persistence.JpaPreuserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PreuserRepositoryImpl implements PreuserRepository {

    private final JpaPreuserRepository jpaPreuserRepository;

    @Override
    public Preuser save(Preuser preuser) {
        return jpaPreuserRepository.save(preuser);
    }
}
