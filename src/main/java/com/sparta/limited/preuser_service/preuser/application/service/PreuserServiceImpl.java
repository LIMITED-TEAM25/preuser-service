package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserCreateResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserGetResponse;
import com.sparta.limited.preuser_service.preuser.application.mapper.PreuserMapper;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PreuserServiceImpl implements PreuserService {

    private final PreuserRepository preuserRepository;

    @Override
    public PreuserCreateResponse createPreuser(PreuserCreateRequest request) {
        Preuser preuser = PreuserMapper.toEntity(request);
        Preuser saved = preuserRepository.save(preuser);

        return PreuserMapper.toPreuserCreateResponse(saved);
    }

    @Override
    public PreuserGetResponse getPreuser(UUID preuserId) {
        Preuser preuser = preuserRepository.findById(preuserId);

        return PreuserMapper.toPreuserGetResponse(preuser);
    }
}
