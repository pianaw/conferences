package org.waveaccess.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.waveaccess.conferences.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
