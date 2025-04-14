package com.sparta.limited.preuser_service.preuser.domain.model;

import com.sparta.limited.common_module.common.BaseEntity;
import com.sparta.limited.preuser_service.preuser.domain.limit.GenderLimit;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_preuser")
public class Preuser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "preuser_title", nullable = false, length = 300)
    private String preuserTitle;

    @Column(name = "preuser_count", nullable = false)
    private int preuserCount;

    @Column(name = "gender_limit", length = 10)
    @Enumerated(EnumType.STRING)
    private GenderLimit genderLimit;

    @Column(name = "age_limit")
    private int ageLimit;

    @Column(name = "preuser_product_id", nullable = false)
    private UUID preuserProductId;

    @Column(name = "recruit_start_at", nullable = false)
    private LocalDateTime recruitStartAt;

    @Column(name = "recruit_end_at", nullable = false)
    private LocalDateTime recruitEndAt;

    @Column(name = "preuser_start_at", nullable = false)
    private LocalDateTime preuserStartAt;

    @Column(name = "preuser_end_at", nullable = false)
    private LocalDateTime preuserEndAt;

    @Column(name = "preuser_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PreuserStatus preuserStatus;

    @Column(name = "announce_date", nullable = false)
    private LocalDateTime announceDate;

    @OneToMany(mappedBy = "preuser")
    private List<PreuserUser> preuserUserList = new ArrayList<>();

    private Preuser(
        String preuserTitle,
        int preuserCount,
        GenderLimit genderLimit,
        int ageLimit,
        UUID preuserProductId,
        LocalDateTime recruitStartAt,
        LocalDateTime recruitEndAt,
        LocalDateTime preuserStartAt,
        LocalDateTime preuserEndAt,
        PreuserStatus preuserStatus,
        LocalDateTime announceDate
    ) {
        this.preuserTitle = preuserTitle;
        this.preuserCount = preuserCount;
        this.genderLimit = genderLimit;
        this.ageLimit = ageLimit;
        this.preuserProductId = preuserProductId;
        this.recruitStartAt = recruitStartAt;
        this.recruitEndAt = recruitEndAt;
        this.preuserStartAt = preuserStartAt;
        this.preuserEndAt = preuserEndAt;
        this.preuserStatus = preuserStatus;
        this.announceDate = announceDate;
    }

    public static Preuser of(
        String preuserTitle,
        int preuserCount,
        GenderLimit genderLimit,
        int ageLimit,
        UUID preuserProductId,
        LocalDateTime recruitStartAt,
        LocalDateTime recruitEndAt,
        LocalDateTime preuserStartAt,
        LocalDateTime preuserEndAt,
        PreuserStatus preuserStatus,
        LocalDateTime announceDate
    ) {
        return new Preuser(
            preuserTitle,
            preuserCount,
            genderLimit,
            ageLimit,
            preuserProductId,
            recruitStartAt,
            recruitEndAt,
            preuserStartAt,
            preuserEndAt,
            preuserStatus,
            announceDate
        );
    }

    public void updateStatus(PreuserStatus status) {
        this.preuserStatus = status;
    }
}
