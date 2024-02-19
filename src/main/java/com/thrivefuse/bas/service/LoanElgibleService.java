package com.thrivefuse.bas.service;

import com.thrivefuse.bas.dto.UserDetails;
import com.thrivefuse.bas.integration.DummyDataServiceClient;
import com.thrivefuse.bas.integration.UserManagementServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class LoanElgibleService {

    @Autowired
    DummyDataServiceClient dummyDataServiceClient;

    @Autowired
    UserManagementServiceClient userManagementServiceClient;

    public Double getMaxElgibleLoanAmountOfUser(String userId) {
        try {
            ResponseEntity<UserDetails> userInfo = userManagementServiceClient.getUserInfoByUserId(userId);
            UserDetails userDetails = userInfo.getBody();
            ResponseEntity<String> userMaxElgibleLoanAmount = dummyDataServiceClient.getUserMaxElgibleLoanAmount(userDetails.getUserPanAndAdharDetails().getPanCardNumber());
            ResponseEntity<String> userCurrentOpenLoans = dummyDataServiceClient.getUserCurrentOpenLoans(userDetails.getUserPanAndAdharDetails().getPanCardNumber());

            ResponseEntity<String> userCreditScore = dummyDataServiceClient.getUserCreditScore(userDetails.getUserPanAndAdharDetails().getPanCardNumber());
            if (Integer.parseInt(userCurrentOpenLoans.getBody()) >= 1 && Integer.parseInt(userCreditScore.getBody()) < 500) {
                return 0.00;
            } else if (Integer.parseInt(userCurrentOpenLoans.getBody()) >= 1 && (Integer.parseInt(userCreditScore.getBody()) > 500 && Integer.parseInt(userCreditScore.getBody()) < 650)) {
                double maxElgibleLoamAmount = Integer.parseInt(userCreditScore.getBody());
                maxElgibleLoamAmount = maxElgibleLoamAmount * 0.6;
                return maxElgibleLoamAmount;
            } else {
                return Double.parseDouble(userMaxElgibleLoanAmount.getBody());
            }
        }catch (Exception ex){
            return null;
        }
    }
}
