package com.example.vidium.repository;

import com.yourblog.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByUsername(String email);


    @Query("SELECT u FROM User u WHERE u.isActivate = true")
    List<User> findAllActiveUsers();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = false WHERE u.id = ?1")
    void deactivateUser(Long userId);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.author.id = ?1")
    Integer countUserPosts(Long userId);
}