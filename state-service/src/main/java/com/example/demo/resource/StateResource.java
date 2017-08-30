package com.example.demo.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.State;
import com.example.demo.service.StateService;

@RestController
@RequestMapping(value = "/")
public class StateResource {

	@Autowired
	private StateService stateService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<State>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body( stateService.findAll() );
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<State> findById(@PathVariable("id") Long id) {
		State state = stateService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(state);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody State state) {
		state = stateService.insert(state);
		
		URI uri = ServletUriComponentsBuilder
						.fromCurrentRequest()
					  	.path("/{id}")
					  	.buildAndExpand(state.getId())
					  	.toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody State state, @PathVariable("id") Long id) {
		state.setId(id);
		stateService.update(state);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		stateService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
