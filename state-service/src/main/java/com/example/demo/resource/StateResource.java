package com.example.demo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.State;
import com.example.demo.service.StateService;

@RestController
@RequestMapping(value = "/")
public class StateResource {

	@Autowired
	private StateService stateService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<State> findAll() {
		return stateService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public State findById(@PathVariable("id") Long id) {
		State state = stateService.findById(id);
		return state;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public State insert(@Valid @RequestBody State state) {
		state = stateService.insert(state);
		return state;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody State state, @PathVariable("id") Long id) {
		state.setId(id);
		stateService.update(state);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		stateService.delete(id);
	}
	
}
