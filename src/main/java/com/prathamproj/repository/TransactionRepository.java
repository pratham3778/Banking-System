package com.prathamproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prathamproj.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{
	
}
