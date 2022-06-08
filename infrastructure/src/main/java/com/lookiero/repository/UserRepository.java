package com.lookiero.repository;

import com.lookiero.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBirthDateBetween(final LocalDate dateStart, final LocalDate dateEnd);

    Optional<User> findByUsername(final String username);
}
