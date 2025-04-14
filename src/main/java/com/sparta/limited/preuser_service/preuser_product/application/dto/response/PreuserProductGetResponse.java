package com.sparta.limited.preuser_service.preuser_product.application.dto.response;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class PreuserProductGetResponse {

    private UUID preuserProductId;
    private UUID productId;
    private String productTitle;
    private String description;
    private int quantity;
    private BigDecimal price;

    private PreuserProductGetResponse(
            UUID preuserProductId,
            UUID productId,
            String productTitle,
            String description,
            int quantity,
            BigDecimal price
    ) {
        this.preuserProductId = preuserProductId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.description = description;
        this.quantity = quantity;
        this.price = price;

    }

    public static PreuserProductGetResponse of(
            UUID preuserProductId,
            UUID productId,
            String productTitle,
            String description,
            int quantity,
            BigDecimal price) {
        return new PreuserProductGetResponse(
                preuserProductId,
                productId,
                productTitle,
                description,
                quantity,
                price
        );
    }

}
