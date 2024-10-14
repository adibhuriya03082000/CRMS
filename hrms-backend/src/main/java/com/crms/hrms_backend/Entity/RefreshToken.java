package com.crms.hrms_backend.Entity;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    private Long id;
    private String username;
    private String token;
    private Instant expiryDate;
    private boolean expired;

}
