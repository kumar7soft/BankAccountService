package com.thrivefuse.bas.integration;

import com.thrivefuse.bas.dto.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FeignEmailNotification", url = "http://localhost:8081") // Replace with the actual service name and URL

public interface UserManagementServiceClient {
    @GetMapping("/userInfoByUserName")
    ResponseEntity<UserDetails> getUserInfoByUserId(@RequestParam String  userId);
}



