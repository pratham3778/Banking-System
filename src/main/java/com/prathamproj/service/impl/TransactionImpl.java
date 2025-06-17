package com.prathamproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prathamproj.dto.TransactionDto;
import com.prathamproj.entity.Transaction;
import com.prathamproj.repository.TransactionRepository;

@Component
public class TransactionImpl implements TransactionService{

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public void saveTransaction(TransactionDto transactionDto) {
		Transaction transaction = Transaction.builder()
				.transactionType(transactionDto.getTransactionType())
				.accountNumber(transactionDto.getAccountNumber())
				.amount(transactionDto.getAmount())
				.status("SUCCESS")
				.build();
		
		transactionRepository.save(transaction);
		System.out.println("Transaction saved successfully");
	}

}
