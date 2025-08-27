package com.example.hello_world_api.controller;

import com.example.hello_world_api.dto.ContactResponse;
import com.example.hello_world_api.dto.TransferRequest;
import com.example.hello_world_api.dto.TransferResponse;
import com.example.hello_world_api.entity.User;
import com.example.hello_world_api.service.TransferService;
import com.example.hello_world_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfer")
@Tag(name = "Transfer", description = "Transfer points operations")
@SecurityRequirement(name = "bearer-key")
public class TransferController {
    
    @Autowired
    private TransferService transferService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    @Operation(summary = "Transfer points to another user", description = "Transfer points from current user to another user by member ID")
    @ApiResponse(responseCode = "200", description = "Transfer successful")
    @ApiResponse(responseCode = "400", description = "Invalid request or insufficient balance")
    @ApiResponse(responseCode = "404", description = "Recipient not found")
    public ResponseEntity<?> transferPoints(
            @Valid @RequestBody TransferRequest request,
            Authentication authentication) {
        try {
            User currentUser = userService.findByEmail(authentication.getName());
            TransferResponse response = transferService.transferPoints(currentUser, request);
            
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Transfer successful");
            result.put("transfer", response);
            result.put("remainingBalance", currentUser.getPoints());
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/history")
    @Operation(summary = "Get transfer history", description = "Get paginated transfer history for current user")
    public ResponseEntity<Page<TransferResponse>> getTransferHistory(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        Page<TransferResponse> history = transferService.getTransferHistory(currentUser, page, size);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/contacts")
    @Operation(summary = "Get recent contacts", description = "Get recent contacts from transfer history")
    public ResponseEntity<List<ContactResponse>> getRecentContacts(
            @Parameter(description = "Number of contacts to return") @RequestParam(defaultValue = "10") int limit,
            Authentication authentication) {
        User currentUser = userService.findByEmail(authentication.getName());
        List<ContactResponse> contacts = transferService.getRecentContacts(currentUser, limit);
        return ResponseEntity.ok(contacts);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search user by member ID", description = "Find user by LBK member ID for transfer")
    public ResponseEntity<?> searchUserByMemberId(
            @Parameter(description = "LBK Member ID") @RequestParam String memberId) {
        Optional<ContactResponse> user = transferService.findUserByMemberId(memberId);
        
        if (user.isPresent()) {
            Map<String, Object> result = new HashMap<>();
            result.put("found", true);
            result.put("user", user.get());
            return ResponseEntity.ok(result);
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("found", false);
            result.put("message", "User not found with member ID: " + memberId);
            return ResponseEntity.ok(result);
        }
    }
}
