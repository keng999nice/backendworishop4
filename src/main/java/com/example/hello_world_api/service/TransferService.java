package com.example.hello_world_api.service;

import com.example.hello_world_api.dto.ContactResponse;
import com.example.hello_world_api.dto.TransferRequest;
import com.example.hello_world_api.dto.TransferResponse;
import com.example.hello_world_api.entity.Transfer;
import com.example.hello_world_api.entity.User;
import com.example.hello_world_api.repository.TransferRepository;
import com.example.hello_world_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferService {
    
    @Autowired
    private TransferRepository transferRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public TransferResponse transferPoints(User fromUser, TransferRequest request) {
        // Find recipient by member ID
        Optional<User> toUserOpt = userRepository.findByMemberId(request.getToMemberId());
        if (!toUserOpt.isPresent()) {
            throw new RuntimeException("Recipient not found with member ID: " + request.getToMemberId());
        }
        
        User toUser = toUserOpt.get();
        
        // Check if trying to transfer to self
        if (fromUser.getId().equals(toUser.getId())) {
            throw new RuntimeException("Cannot transfer points to yourself");
        }
        
        // Check if sender has sufficient balance
        if (fromUser.getPoints() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }
        
        // Perform the transfer
        fromUser.setPoints(fromUser.getPoints() - request.getAmount());
        toUser.setPoints(toUser.getPoints() + request.getAmount());
        
        // Save updated balances
        userRepository.save(fromUser);
        userRepository.save(toUser);
        
        // Create transfer record
        Transfer transfer = new Transfer(fromUser, toUser, request.getAmount(), request.getDescription());
        transfer = transferRepository.save(transfer);
        
        return new TransferResponse(transfer);
    }
    
    public Page<TransferResponse> getTransferHistory(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transfer> transfers = transferRepository.findByUserInvolved(user, pageable);
        return transfers.map(TransferResponse::new);
    }
    
    public List<ContactResponse> getRecentContacts(User user, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<User> contacts = transferRepository.findRecentContacts(user, pageable);
        return contacts.stream().map(ContactResponse::new).collect(Collectors.toList());
    }
    
    public Optional<ContactResponse> findUserByMemberId(String memberId) {
        Optional<User> user = userRepository.findByMemberId(memberId);
        return user.map(ContactResponse::new);
    }
}
