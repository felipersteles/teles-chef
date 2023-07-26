package br.teles.chef.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.teles.chef.domain.model.User;
import br.teles.chef.domain.security.RefreshToken;
import br.teles.chef.repo.RefreshTokenRepo;
import br.teles.chef.repo.UserRepo;
import br.teles.chef.service.excpetion.TokenRefreshException;
import jakarta.transaction.Transactional;

@Service
public class RefreshTokenService {
    @Value("${teles.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepo refreshTokenRepository;

    @Autowired
    private UserRepo userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {

        User user = userRepository.findById(userId).get();

        if (refreshTokenRepository.existsByUser(user)) {
            System.out.println("Tem que deletar token");
            refreshTokenRepository.deleteByUser(user);
        }

        RefreshToken refreshToken = RefreshToken.builder().user(user)
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs)).token(UUID.randomUUID().toString())
                .build();

        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        return savedRefreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
