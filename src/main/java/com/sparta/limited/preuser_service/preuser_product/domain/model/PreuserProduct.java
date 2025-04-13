package com.sparta.limited.preuser_service.preuser_product.domain.model;

import com.sparta.limited.common_module.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_preuser_product")
public class PreuserProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    private PreuserProduct(
        String title,
        String description,
        BigDecimal price,
        UUID productId,
        int quantity) {
        this.title = title;
        this.description = description;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    private PreuserProduct(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static PreuserProduct of(
        UUID productId,
        int quantity) {
        return new PreuserProduct(
            productId,
            quantity
        );
    }

    public static PreuserProduct ofFegin(
        String title,
        String description,
        BigDecimal price,
        UUID productId,
        int quantity) {
        return new PreuserProduct(
            title,
            description,
            price,
            productId,
            quantity
        );
    }
}
