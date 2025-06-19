package com.prathamproj.controller;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.prathamproj.dto.*;
import com.prathamproj.service.impl.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Account Management APIs", description = "APIs for managing user accounts, including creation, balance check, credit, debit, and transfer operations")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(
        summary = "Create New User Account",
        description = "Creates a new user account and assigns a unique account ID"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
    
    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto) {
    	return userService.login(loginDto);
    }

    @Operation(
        summary = "Balance Enquiry",
        description = "Returns the current account balance for a given account number"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @Operation(
        summary = "Name Enquiry",
        description = "Retrieves the account holder's name for a given account number"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);
    }

    @Operation(
        summary = "Credit Account",
        description = "Credits (adds) a specified amount to the user's account"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request) {
        return userService.creditAccount(request);
    }

    @Operation(
        summary = "Debit Account",
        description = "Debits (subtracts) a specified amount from the user's account"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request) {
        return userService.debitAccount(request);
    }

    @Operation(
        summary = "Transfer Funds",
        description = "Transfers funds from one account to another"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request) {
        return userService.transfer(request);
    }
}
