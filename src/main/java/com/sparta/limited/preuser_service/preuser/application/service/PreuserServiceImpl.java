package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.*;
import com.sparta.limited.preuser_service.preuser.application.mapper.PreuserMapper;
import com.sparta.limited.preuser_service.preuser.application.validate.ApplyPreuserPeriodValidator;
import com.sparta.limited.preuser_service.preuser.application.validate.ApplyPreuserUserAgeValidator;
import com.sparta.limited.preuser_service.preuser.application.validate.ApplyPreuserUserGenderValidator;
import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserUserRepository;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import com.sparta.limited.preuser_service.preuser.infrastructure.client.UserClient;
import com.sparta.limited.preuser_service.preuser.infrastructure.dto.response.UserSearchUserIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PreuserServiceImpl implements PreuserService {

    private final PreuserRepository preuserRepository;
    private final PreuserUserRepository preuserUserRepository;
    private final UserClient userClient;

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

    @Override
    @Transactional
    public PreuserEventApplyResponse applyPreuserEvents(UUID preuserId, Long userId) {


        Preuser preuser = preuserRepository.findWithPessimisticLockById(preuserId);

        preuserUserRepository.existsByPreuserIdAndUserId(preuserId, userId);

        if (!ApplyPreuserPeriodValidator.validateApplyPeriod(preuser.getRecruitStartAt(), preuser.getRecruitEndAt())) {
            throw new BusinessException(ErrorCode.UNMATCHED_USER_DATA, "신청기간이 아닙니다");
        }

        UserSearchUserIdResponse response = userClient.getUserById(userId);

        if (!ApplyPreuserUserAgeValidator.validateAgeLimit(preuser.getAgeLimit(), response.getAge())) {
            throw new BusinessException(ErrorCode.UNMATCHED_USER_DATA, "신청 가능한 나이가 아닙니다");
        }

        if (!ApplyPreuserUserGenderValidator.validateUserGenderLimit(preuser.getGenderLimit(), response.getGender())) {
            throw new BusinessException(ErrorCode.UNMATCHED_USER_DATA, "신청 가능한 성별이 아닙니다");
        }

        PreuserUser preuserUser = PreuserUser.of(userId, preuser);

        preuserUserRepository.save(preuserUser);

        return PreuserEventApplyResponse.of(preuser.getId(), preuser.getPreuserTitle(), "신청 완료");
    }
}
