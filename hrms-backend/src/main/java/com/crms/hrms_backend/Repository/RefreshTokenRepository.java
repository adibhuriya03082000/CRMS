package com.crms.hrms_backend.Repository;

import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Entity.RefreshToken;

public interface RefreshTokenRepository {

    void saveRefreshTokenDetail(RefreshToken refreshToken);

}
