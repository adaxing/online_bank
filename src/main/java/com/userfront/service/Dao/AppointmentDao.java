package com.userfront.service.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment, Long>{
	List<Appointment> findAll();

	Appointment findAllById(Long id);
}