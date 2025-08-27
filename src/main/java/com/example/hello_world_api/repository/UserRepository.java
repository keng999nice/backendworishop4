package com.example.hello_world_api.repository;

import com.example.hello_world_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByMemberId(String memberId);
    boolean existsByMemberId(String memberId);
}
