package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.State;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findAll() {
		return stateRepository.findAll();
	}
	
	public State findById(Long id) {
		State state = stateRepository.findOne(id);
		
		if (state == null)
			throw new RecordNotFoundException("Record not found with id " + id);
		
		return state;
	}

	public State insert(State state) {
		state.setId(null);
		return stateRepository.save(state);
	}
	
	public void update(State state) {
		verifyRecord(state);
		stateRepository.save(state);
	}
	
	public void delete(Long id) {
		try {
			stateRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecordNotFoundException("Record not found with id " + id);
		}
	}
	
	/**
	 * Just semantics 
	 */
	private void verifyRecord(State state) {
		findById(state.getId());
	}
}
