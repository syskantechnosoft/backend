package com.demo.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.exceptions.RecordNotFoundException;
import com.demo.backend.exceptions.ValidationFailedException;
import com.demo.backend.services.interfaces.ICustomerService;
import com.demo.backend.viewmodels.CustomerCreateViewModel;
import com.demo.backend.viewmodels.CustomerLookupViewModel;
import com.demo.backend.viewmodels.CustomerViewModel;

@RestController
@RequestMapping("api/v1/customers")
@CrossOrigin
public class CustomerController {

	private final ICustomerService customerService;

	public CustomerController(ICustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	private ResponseEntity<List<CustomerViewModel>> get() {
		return new ResponseEntity(this.customerService.get(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	private ResponseEntity<CustomerViewModel> get(@PathVariable int id) {
		try {
			return new ResponseEntity(this.customerService.get(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/lookup")
	private ResponseEntity<List<CustomerLookupViewModel>> getLookup() {
		return new ResponseEntity(this.customerService.getLookup(), HttpStatus.OK);
	}

	@PostMapping
	private ResponseEntity create(@RequestBody CustomerCreateViewModel viewModel) {
		try {
			this.customerService.create(viewModel);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (ValidationFailedException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
