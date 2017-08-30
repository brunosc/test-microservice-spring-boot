package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.City;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	public City findById(Long id) {
		City city = cityRepository.findOne(id);
		
		if (city == null)
			throw new RecordNotFoundException("Record not found with id " + id);
		
		return city;
	}

	public City insert(City city) {
		city.setId(null);
		return cityRepository.save(city);
	}
	
	public void update(City city) {
		verifyRecord(city);
		cityRepository.save(city);
	}
	
	public void delete(Long id) {
		try {
			cityRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecordNotFoundException("Record not found with id " + id);
		}
	}
	
	/**
	 * Just semantics 
	 */
	private void verifyRecord(City city) {
		findById(city.getId());
	}
}
