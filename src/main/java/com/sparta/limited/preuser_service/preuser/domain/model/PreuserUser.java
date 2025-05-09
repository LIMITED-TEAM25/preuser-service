package com.sparta.limited.preuser_service.preuser.domain.model;

import com.sparta.limited.common_module.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_preuser_user")
public class PreuserUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "is_selected", nullable = false)
    private boolean isSelected = false;

    @ManyToOne
    @JoinColumn(name = "preuser_id", nullable = false)
    private Preuser preuser;

    private PreuserUser(
            Long userId,
            Preuser preuser,
            boolean isSelected
    ) {
        this.userId = userId;
        this.preuser = preuser;
        this.isSelected = isSelected;
    }

    public static PreuserUser of(
            Long userId,
            Preuser preuser,
            boolean isSelected
    ) {
        return new PreuserUser(
                userId,
                preuser,
                isSelected
        );
    }

    public void select() {
        this.isSelected = true;
    }
}
