package com.sparta.limited.preuser_service.preuser_product.presentation;

import com.sparta.limited.preuser_service.preuser_product.application.dto.request.PreuserProductCreateRequest;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductCreateResponse;
import com.sparta.limited.preuser_service.preuser_product.application.servcie.PreuserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/preuser-products/")
public class PreuserProductController {

    private final PreuserProductService preuserProductService;

    @PostMapping
    public ResponseEntity<PreuserProductCreateResponse> createPreuserProduct(
        @RequestBody PreuserProductCreateRequest request) {
        PreuserProductCreateResponse response = preuserProductService.createPreuserProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
