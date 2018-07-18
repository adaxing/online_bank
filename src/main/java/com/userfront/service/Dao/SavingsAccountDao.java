package com.userfront.service.Dao;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long>{
	SavingsAccount findByAccountNumber(int accountNumber);

}
