package com.prathamproj.service.impl;

import com.prathamproj.dto.BankResponse;
import com.prathamproj.dto.CreditDebitRequest;
import com.prathamproj.dto.EnquiryRequest;
import com.prathamproj.dto.LoginDto;
import com.prathamproj.dto.TransferRequest;
import com.prathamproj.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);
    BankResponse creditAccount(CreditDebitRequest request);
    BankResponse debitAccount(CreditDebitRequest request);
    BankResponse transfer(TransferRequest request);
    BankResponse login(LoginDto loginDto);
}
