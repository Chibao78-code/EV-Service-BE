package com.swp.evmsystem.repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Integer> {

    Optional<PasswordResetTokenEntity> findByEmailAndOtpCodeAndIsUsedFalse(String email, String otpCode);

    Optional<PasswordResetTokenEntity> findTopByEmailAndIsUsedFalseOrderByCreatedAtDesc(String email);

    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}
