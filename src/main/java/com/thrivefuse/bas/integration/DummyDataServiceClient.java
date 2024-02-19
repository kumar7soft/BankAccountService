package com.thrivefuse.bas.integration;

import com.thrivefuse.bas.dto.UserCreditEnquiry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FeignEmailNotification", url = "http://localhost:8081") // Replace with the actual service name and URL
public interface DummyDataServiceClient {


    @GetMapping("/userMaxElgibleLoanAmt")
    ResponseEntity<String> getUserMaxElgibleLoanAmount(@RequestParam String  panCardNumber);

    @GetMapping("/userCurrentOpenLoans")
    ResponseEntity<String> getUserCurrentOpenLoans(@RequestParam String  panCardNumber);

    @PostMapping("/postUserCreditEnquiry")
    ResponseEntity<String> postUserCreditEnquiry(@RequestBody UserCreditEnquiry userCreditEnquiry);
    @GetMapping("/userCreditScore")
    ResponseEntity<String> getUserCreditScore(@RequestParam String  panCardNumber);

}
