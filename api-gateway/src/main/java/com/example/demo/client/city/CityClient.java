package com.example.demo.client.city;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "city-service")
public interface CityClient {

	@RequestMapping(value="/", method=RequestMethod.GET)
	List<City> findAll();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	City findById(@PathVariable("id") Long id);
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	City insert(@RequestBody City city);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	void update(@RequestBody City city, @PathVariable("id") Long id);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable("id") Long id);
	
}
