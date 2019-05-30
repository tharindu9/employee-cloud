package com.allocationservice.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allocationservice.modal.Allocation;
import com.allocationservice.repository.AllocationRepository;


@Service
public class AllocationServiceImpl implements AllocationService{

	@Autowired
	AllocationRepository allocationRepo;
	
	@Override
	public Allocation save(Allocation allocation) {
		return allocationRepo.save(allocation);
	}
	
	@Override
	public List<Allocation> findAll(){
		return allocationRepo.findAll();
	}
	
	@Override
	public Allocation fetchAlocation(Allocation allocation){
		Optional<Allocation>  optional = allocationRepo.findById(allocation.getId());
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public List<Allocation> findByEid(Integer id){
		return allocationRepo.findByEmpId(id);
	}

}
