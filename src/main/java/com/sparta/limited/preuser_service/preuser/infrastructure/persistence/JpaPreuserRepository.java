package com.sparta.limited.preuser_service.preuser.infrastructure.persistence;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPreuserRepository extends JpaRepository<Preuser, UUID> {

}
