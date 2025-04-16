package com.sparta.limited.preuser_service.preuser.presentation;

import com.sparta.limited.common_module.common.aop.RoleCheck;
import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserUpdateStatusRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.*;
import com.sparta.limited.preuser_service.preuser.application.service.PreuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/preusers")
public class PreuserController {

    private final PreuserService preuserService;


    @PostMapping
    @RoleCheck("ROLE_ADMIN")
    public ResponseEntity<PreuserCreateResponse> createPreuser(
            @RequestBody PreuserCreateRequest request) {
        PreuserCreateResponse response = preuserService.createPreuser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{preuserId}")
    public ResponseEntity<PreuserGetResponse> getPreuser(
            @PathVariable UUID preuserId
    ) {
        PreuserGetResponse response = preuserService.getPreuser(preuserId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{preuserId}/status")
    @RoleCheck("ROLE_ADMIN")
    public ResponseEntity<PreuserUpdateStatusResponse> updatePreuserStatus(
            @PathVariable UUID preuserId,
            @RequestBody PreuserUpdateStatusRequest preuserStatus
    ) {
        PreuserUpdateStatusResponse response = preuserService.updatePreuserStatus(preuserId,
                preuserStatus.getPreuserStatus());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PreuserGetForPageResponse>> getAllPreuser(
            Pageable pageable
    ) {
        Page<PreuserGetForPageResponse> responses = preuserService.getAllPreuesr(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping("/events/{preuserId}")
    @RoleCheck("ROLE_USER")
    public ResponseEntity<PreuserEventApplyResponse> applyPreuserEvents(
            @PathVariable UUID preuserId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        PreuserEventApplyResponse response = preuserService.applyPreuserEvents(preuserId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("{preuserId}/preuserUser")
    @RoleCheck("ROLE_ADMIN")
    public ResponseEntity<PreuserUserSelectResponse> selectPreuserUser(
            @PathVariable UUID preuserId
    ) {
        PreuserUserSelectResponse response = preuserService.selectPreuserUser(preuserId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
