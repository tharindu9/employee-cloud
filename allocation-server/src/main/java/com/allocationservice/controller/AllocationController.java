package com.allocationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allocationservice.modal.Allocation;
import com.allocationservice.server.AllocationService;



@RestController
@RequestMapping(value = "/emscloud")
public class AllocationController {

	@Autowired
	AllocationService allocationService;
	
	@RequestMapping(value = "/allocation" , method = RequestMethod.GET )
	public List<Allocation> fetchAll() {
		return allocationService.findAll();
		
	}
	
	@RequestMapping( value = "allocation", method = RequestMethod.POST )
	public Allocation save(@RequestBody Allocation allocation) {
		return allocationService.save(allocation);
		
	}
	
	@RequestMapping(value = "allocation/{id}" , method = RequestMethod.GET )
	public ResponseEntity<Allocation> fetchAllocation(@PathVariable Integer id) {
		Allocation allocation = new Allocation();
		allocation.setId(id);
		Allocation allocation1 = allocationService.fetchAlocation(allocation);
		
		if(allocation1==null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			return new ResponseEntity<Allocation>(allocation1,HttpStatus.OK);
		}
	}
	

	@RequestMapping( value = "allocation/employee/{id}" , method = RequestMethod.GET )
	public List<Allocation> fetchAllByEid(@PathVariable Integer id) {

		return allocationService.findByEid(id);
		
	}
}
