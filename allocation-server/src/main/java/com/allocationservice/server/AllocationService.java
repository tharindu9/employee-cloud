package com.allocationservice.server;

import java.util.List;

import com.allocationservice.modal.Allocation;

public interface AllocationService {

	Allocation save(Allocation allocation);
	List<Allocation> findAll();
	Allocation fetchAlocation(Allocation allocation);
	List<Allocation> findByEid(Integer id);
}
