package com.sparta.limited.preuser_service.preuser.application.service;

import com.sparta.limited.preuser_service.preuser.domain.model.Preuser;
import com.sparta.limited.preuser_service.preuser.domain.repository.PreuserRepository;
import com.sparta.limited.preuser_service.preuser.domain.status.PreuserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PreuserStatusScheduler {

    private final PreuserRepository preuserRepository;

    @Scheduled(cron = "* * 0 * * *")
    @Transactional
    public void updatePreuserStatus() {
        List<Preuser> recruitingPreuserList =
                preuserRepository.findByRecruitEndAtBeforeAndPreuserStatus(LocalDateTime.now(), PreuserStatus.RECRUITING);
        for (Preuser preuser : recruitingPreuserList) {
            preuser.updateStatus(PreuserStatus.CLOSED);
        }
        log.info("Preuser status updated");
        List<Preuser> planedPreuserList =
                preuserRepository.findByRecruitStartAtBeforeAndPreuserStatus(LocalDateTime.now(), PreuserStatus.PLANNED);
        for (Preuser preuser : planedPreuserList) {
            preuser.updateStatus(PreuserStatus.RECRUITING);
        }

    }
}
