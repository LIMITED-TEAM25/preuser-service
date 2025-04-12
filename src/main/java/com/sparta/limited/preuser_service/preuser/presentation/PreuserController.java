package com.sparta.limited.preuser_service.preuser.presentation;

import com.sparta.limited.preuser_service.preuser.application.dto.request.PreuserCreateRequest;
import com.sparta.limited.preuser_service.preuser.application.dto.response.PreuserCreateResponse;
import com.sparta.limited.preuser_service.preuser.application.service.PreuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/preuser")
public class PreuserController {

    private final PreuserService preuserService;


    @PostMapping
    public ResponseEntity<PreuserCreateResponse> createPreuser(
        @RequestBody PreuserCreateRequest request) {
        PreuserCreateResponse response = preuserService.createPreuser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
