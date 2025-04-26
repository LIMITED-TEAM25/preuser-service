package com.sparta.limited.preuser_service.preuser_product.application.dto.response;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PreuserProductCreateResponse {


    private UUID productId;

    private String title;

    private String description;


    private PreuserProductCreateResponse(UUID productId, String title, String description
    ) {
        this.productId = productId;
        this.title = title;
        this.description = description;
    }


    public static PreuserProductCreateResponse of(
        UUID productId,
        String title,
        String description
    ) {
        return new PreuserProductCreateResponse(
            productId,
            title,
            description
        );
    }

}
