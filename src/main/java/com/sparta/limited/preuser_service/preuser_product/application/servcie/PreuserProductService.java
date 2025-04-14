package com.sparta.limited.preuser_service.preuser_product.application.servcie;

import com.sparta.limited.preuser_service.preuser_product.application.dto.request.PreuserProductCreateRequest;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductCreateResponse;
import com.sparta.limited.preuser_service.preuser_product.application.dto.response.PreuserProductGetResponse;

import java.util.UUID;


public interface PreuserProductService {

    PreuserProductCreateResponse createPreuserProduct(PreuserProductCreateRequest request);

    PreuserProductGetResponse getPreuserProduct(UUID preuserProductId);
}
