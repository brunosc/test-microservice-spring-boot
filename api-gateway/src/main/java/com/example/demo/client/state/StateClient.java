package com.example.demo.client.state;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "state-service")
public interface StateClient {

	@RequestMapping(value="/", method=RequestMethod.GET)
	List<State> findAll();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	State findById(@PathVariable("id") Long id);
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	State insert(@RequestBody State state);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	void update(@RequestBody State state, @PathVariable("id") Long id);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable("id") Long id);
}
