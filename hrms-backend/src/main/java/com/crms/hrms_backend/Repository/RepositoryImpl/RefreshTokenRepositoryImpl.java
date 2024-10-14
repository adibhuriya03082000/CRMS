package com.crms.hrms_backend.Repository.RepositoryImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Entity.RefreshToken;
import com.crms.hrms_backend.Repository.RefreshTokenRepository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final JdbcTemplate jdbcTemplate;

    public RefreshTokenRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveRefreshTokenDetail(RefreshToken refreshToken) {
        jdbcTemplate.update("INSERT INTO `refresh_token` (`username`,`token`,`expiry_date`,`expired`) VALUES (?,?,?,?)", refreshToken.getUsername(), refreshToken.getToken(), refreshToken.getExpiryDate(), refreshToken.isExpired());
    }

}
