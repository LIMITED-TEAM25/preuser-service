package com.sparta.limited.preuser_service.preuser_product.application.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PreuserProductCreateRequest {

    private UUID productId;
    private int quantity;

}
