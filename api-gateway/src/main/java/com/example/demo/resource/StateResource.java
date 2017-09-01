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

import com.example.demo.client.state.State;
import com.example.demo.client.state.StateClient;

@RestController
@RequestMapping("/state")
public class StateResource {

	@Autowired
	private StateClient stateClient;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<State>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body( stateClient.findAll() );
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<State> findById(@PathVariable("id") Long id) {
		State state = stateClient.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(state);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody State state) {
		state = stateClient.insert(state);
		
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
		stateClient.update(state, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		stateClient.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
