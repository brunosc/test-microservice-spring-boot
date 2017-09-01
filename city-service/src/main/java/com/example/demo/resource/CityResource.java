package com.example.demo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.City;
import com.example.demo.service.CityService;

@RestController
@RequestMapping(value = "/")
public class CityResource {

	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<City> findAll() {
		return cityService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public City findById(@PathVariable("id") Long id) {
		City city = cityService.findById(id);
		return city;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public City insert(@Valid @RequestBody City city) {
		city = cityService.insert(city);
		return city;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody City city, @PathVariable("id") Long id) {
		city.setId(id);
		cityService.update(city);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		cityService.delete(id);
	}
	
}
