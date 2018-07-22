package com.userfront.service.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.Recipient;

public interface recipientDao extends CrudRepository<Recipient, Long> {
	List<Recipient> findAll();
	Recipient findByName(String recipientName);
	void deleteByName(String recipientName);
}
