package com.example.hello_world_api.repository;

import com.example.hello_world_api.entity.Transfer;
import com.example.hello_world_api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
    // Find transfers where user is either sender or receiver
    @Query("SELECT t FROM Transfer t WHERE t.fromUser = :user OR t.toUser = :user ORDER BY t.createdAt DESC")
    Page<Transfer> findByUserInvolved(@Param("user") User user, Pageable pageable);
    
    // Find transfers sent by user
    Page<Transfer> findByFromUserOrderByCreatedAtDesc(User fromUser, Pageable pageable);
    
    // Find transfers received by user
    Page<Transfer> findByToUserOrderByCreatedAtDesc(User toUser, Pageable pageable);
    
    // Find recent contacts (users who have been involved in transfers)
    @Query("SELECT DISTINCT CASE WHEN t.fromUser = :user THEN t.toUser ELSE t.fromUser END " +
           "FROM Transfer t WHERE t.fromUser = :user OR t.toUser = :user " +
           "ORDER BY t.createdAt DESC")
    List<User> findRecentContacts(@Param("user") User user, Pageable pageable);
}
