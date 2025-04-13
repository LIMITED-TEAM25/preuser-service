package com.sparta.limited.preuser_service.preuser_product.infrastructure.dto.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductReadResponse {

    private final UUID id;
    private final String title;
    private final String description;
    private final BigDecimal price;

    private ProductReadResponse(UUID id, String title, String description, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public static ProductReadResponse of(UUID id, String title, String description,
        BigDecimal price) {
        return new ProductReadResponse(id, title, description, price);
    }

}
