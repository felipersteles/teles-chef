package br.teles.chef.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import br.teles.chef.domain.model.User;
import br.teles.chef.domain.security.RefreshToken;
import jakarta.transaction.Transactional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<List<RefreshToken>> findByUser(User user);

    Boolean existsByUser(User user);

    @Modifying
    @Transactional
    Integer deleteByUser(User user);
}
