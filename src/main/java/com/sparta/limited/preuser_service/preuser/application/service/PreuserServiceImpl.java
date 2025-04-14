package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserCreateResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserGetForPageResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserGetResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserUpdateStatusResponse;
import com.sparta.limited.preuser_service.preuser.application.mapper.PreuserMapper;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PreuserServiceImpl implements PreuserService {

    private final PreuserRepository preuserRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public PreuserUpdateStatusResponse updatePreuserStatus(UUID preuserId,
                                                           PreuserStatus preuserStatus) {

        Preuser preuser = preuserRepository.findById(preuserId);

        preuser.updateStatus(preuserStatus);

        return PreuserMapper.toPreuserUpdateStatusResponse(preuser);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PreuserGetForPageResponse> getAllPreuesr(Pageable pageable) {

        Page<Preuser> preuser = preuserRepository.findAll(pageable);

        return preuser.map(PreuserMapper::toPreuserGetForPageResponse);
    }
}
