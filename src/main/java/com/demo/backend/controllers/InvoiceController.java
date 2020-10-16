package com.demo.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.exceptions.RecordNotFoundException;
import com.demo.backend.exceptions.ValidationFailedException;
import com.demo.backend.services.interfaces.IInvoiceService;
import com.demo.backend.viewmodels.InvoiceCreateViewModel;
import com.demo.backend.viewmodels.InvoiceListViewModel;
import com.demo.backend.viewmodels.InvoiceViewModel;

@RestController
@RequestMapping("api/v1/invoices")
public class InvoiceController {
	private final IInvoiceService invoiceService;

	public InvoiceController(IInvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@GetMapping
	private ResponseEntity<List<InvoiceListViewModel>> get() {
		return new ResponseEntity(this.invoiceService.get(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	private ResponseEntity<InvoiceViewModel> get(@PathVariable int id) {
		try {
			return new ResponseEntity(this.invoiceService.get(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	private ResponseEntity create(InvoiceCreateViewModel viewModel) {
		try {
			this.invoiceService.create(viewModel);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (ValidationFailedException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
