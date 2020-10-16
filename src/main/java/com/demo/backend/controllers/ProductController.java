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
import com.demo.backend.services.interfaces.IProductService;
import com.demo.backend.viewmodels.ProductCreateViewModel;
import com.demo.backend.viewmodels.ProductLookupViewModel;
import com.demo.backend.viewmodels.ProductViewModel;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	private final IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	private ResponseEntity<List<ProductViewModel>> get() {
		return new ResponseEntity(this.productService.get(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	private ResponseEntity<ProductViewModel> get(@PathVariable int id) {
		try {
			return new ResponseEntity(this.productService.get(id), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/lookup")
	private ResponseEntity<List<ProductLookupViewModel>> getLookup() {
		return new ResponseEntity(this.productService.getLookup(), HttpStatus.OK);
	}

	@PostMapping
	private ResponseEntity create(ProductCreateViewModel viewModel) {
		try {
			this.productService.create(viewModel);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (ValidationFailedException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
