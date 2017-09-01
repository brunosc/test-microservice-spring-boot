package com.example.demo.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.client.city.City;
import com.example.demo.client.city.CityClient;
import com.example.demo.client.state.StateClient;

@RestController
@RequestMapping("/city")
public class CityResource {

	@Autowired
	private CityClient cityClient;
	
	@Autowired
	private StateClient stateClient;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<City>> findAll() {
		
		List<City> cities = cityClient.findAll();
		
		for (City city: cities)
			city.setState( stateClient.findById(city.getStateId()) );
		
		return ResponseEntity.status(HttpStatus.OK).body( cities );
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<City> findById(@PathVariable("id") Long id) {
		City city = cityClient.findById(id);
		
		city.setState( stateClient.findById(city.getStateId()) );
		
		return ResponseEntity.status(HttpStatus.OK).body(city);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody City city) {
		city = cityClient.insert(city);
		
		URI uri = ServletUriComponentsBuilder
						.fromCurrentRequest()
					  	.path("/{id}")
					  	.buildAndExpand(city.getId())
					  	.toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody City city, @PathVariable("id") Long id) {
		city.setId(id);
		cityClient.update(city, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		cityClient.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
