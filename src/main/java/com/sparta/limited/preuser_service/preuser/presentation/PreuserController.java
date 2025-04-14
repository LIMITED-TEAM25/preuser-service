package com.sparta.limited.preuser_service.preuser.presentation;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserUpdateStatusRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserCreateResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserGetResponse;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserUpdateStatusResponse;
import com.sparta.limited.preuser_service.preuser.application.service.PreuserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/preusers/")
public class PreuserController {

    private final PreuserService preuserService;


    @PostMapping
    public ResponseEntity<PreuserCreateResponse> createPreuser(
        @RequestBody PreuserCreateRequest request) {
        PreuserCreateResponse response = preuserService.createPreuser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{preuserId}")
    public ResponseEntity<PreuserGetResponse> getPreuser(
        @PathVariable UUID preuserId
    ) {
        PreuserGetResponse response = preuserService.getPreuser(preuserId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("{preuserId}/status")
    public ResponseEntity<PreuserUpdateStatusResponse> updatePreuserStatus(
        @PathVariable UUID preuserId,
        @RequestBody PreuserUpdateStatusRequest preuserStatus
    ) {
        PreuserUpdateStatusResponse response = preuserService.updatePreuserStatus(preuserId,
            preuserStatus.getPreuserStatus());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
