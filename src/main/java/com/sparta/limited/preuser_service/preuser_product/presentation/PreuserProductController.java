package com.sparta.limited.preuser_service.preuser_product.presentation;

import com.sparta.limited.common_module.common.aop.RoleCheck;
import com.sparta.limited.preuser_service.preuser_product.application.dto.request.PreuserProductCreateRequest;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductCreateResponse;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductGetResponse;
import com.sparta.limited.preuser_service.preuser_product.application.servcie.PreuserProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/preuser-products/")
public class PreuserProductController {

    private final PreuserProductService preuserProductService;

    @PostMapping
    @RoleCheck("ROLE_ADMIN")
    public ResponseEntity<PreuserProductCreateResponse> createPreuserProduct(
            @RequestBody PreuserProductCreateRequest request) {
        PreuserProductCreateResponse response = preuserProductService.createPreuserProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{preuserProductId}")
    public ResponseEntity<PreuserProductGetResponse> getPreuserProduct(
            @PathVariable UUID preuserProductId
    ) {
        PreuserProductGetResponse response = preuserProductService.getPreuserProduct(preuserProductId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
