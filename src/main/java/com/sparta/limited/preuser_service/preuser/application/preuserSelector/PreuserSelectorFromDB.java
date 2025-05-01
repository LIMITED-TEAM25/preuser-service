package com.sparta.limited.preuser_service.preuser.application.preuserSelector;

import com.sparta.limited.preuser_service.preuser.domain.model.PreuserUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class PreuserSelectorFromDB {

    private PreuserSelectorFromDB() {
    }

    public List<PreuserUser> selectPreuser(
            List<PreuserUser> preuserUserList,
            int selectCount
    ) {
        Collections.shuffle(preuserUserList);

        int limit = Math.min(selectCount, preuserUserList.size());

        List<PreuserUser> selectedPreuserList = preuserUserList.subList(0, limit);

        for (PreuserUser user : selectedPreuserList) {
            user.select();
        }

        return selectedPreuserList;
    }

}
