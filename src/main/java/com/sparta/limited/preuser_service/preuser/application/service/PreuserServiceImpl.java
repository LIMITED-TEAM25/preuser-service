package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.*;
import com.sparta.limited.preuser_service.preuser.application.mapper.PreuserMapper;
import com.sparta.limited.preuser_service.preuser.application.preuserSelector.PreuserSelector;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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

        ApplyPreuserPeriodValidator.validate(preuser.getRecruitStartAt(), preuser.getRecruitEndAt());

        UserSearchUserIdResponse response = userClient.getUserById(userId);

        ApplyPreuserUserAgeValidator.validate(preuser.getAgeLimit(), response.getAge());

        ApplyPreuserUserGenderValidator.validate(preuser.getGenderLimit(), response.getGender());

        PreuserUser preuserUser = PreuserUser.of(userId, preuser, false);

        preuserUserRepository.save(preuserUser);

        return PreuserMapper.toPreuserEventApplyResponse(preuser);
    }

    @Override
    @Transactional
    public PreuserUserSelectResponse selectPreuserUser(UUID preuserId) {

        Preuser preuser = preuserRepository.findWithPessimisticLockById(preuserId);

        preuserUserRepository.existsByPreuserIdAndIsSelectedTrue(preuserId);

        List<PreuserUser> preuserUserList = preuserUserRepository.findByPreuserId(preuserId);

        List<PreuserUser> selectedUsers = PreuserSelector.selectPreuser(
                preuserUserList,
                preuser.getPreuserCount()
        );

        List<UserSearchUserIdResponse> userInfoList = new ArrayList<>();

        for (PreuserUser user : selectedUsers) {
            UserSearchUserIdResponse userInfo = userClient.getUserById(user.getUserId());
            userInfoList.add(userInfo);
        }

        preuserUserRepository.saveAll(selectedUsers);

        return PreuserMapper.toPreuserUserSelectResponse(preuser, userInfoList);
    }

}
