package com.userfront.service.Dao;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long>{
	PrimaryAccount findByAccountNumber(int accountNumber);
}
