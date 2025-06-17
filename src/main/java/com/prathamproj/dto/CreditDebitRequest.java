package com.prathamproj.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDebitRequest {
	
	@Schema(name = "User Account Number")
	private String accountNumber;
	
	@Schema(name = "Transaction Amount")
	private BigDecimal amount;
	
}
