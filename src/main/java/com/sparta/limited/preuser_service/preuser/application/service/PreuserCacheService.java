package com.sparta.limited.preuser_service.preuser.application.service;


import java.time.LocalDateTime;

public interface PreuserCacheService {

    void cacheAllUsersForPreuser(LocalDateTime preuserEndAt, String userRole);
}
