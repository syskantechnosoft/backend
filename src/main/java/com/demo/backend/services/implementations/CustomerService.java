package com.demo.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.demo.backend.exceptions.RecordNotFoundException;
import com.demo.backend.models.Customer;
import com.demo.backend.repositories.CustomerRepository;
import com.demo.backend.services.interfaces.ICustomerService;
import com.demo.backend.viewmodels.CustomerCreateViewModel;
import com.demo.backend.viewmodels.CustomerViewModel;

public class CustomerService implements ICustomerService {
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerViewModel> get() {
		// TODO Auto-generated method stub
		return this.customerRepository.findAll().stream().map(c -> {
			CustomerViewModel viewModel = new CustomerViewModel();
			BeanUtils.copyProperties(c, viewModel);
			return viewModel;
		}).collect(Collectors.toList());
	}

	@Override
	public CustomerViewModel get(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> customerDb = this.customerRepository.findById(id);

		if (customerDb.isEmpty()) {
			throw new RecordNotFoundException("Not found");
		}

		CustomerViewModel viewModel = new CustomerViewModel();
		BeanUtils.copyProperties(customerDb.get(), viewModel);
		return viewModel;
	}

	@Override
	public void create(CustomerCreateViewModel viewModel) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		BeanUtils.copyProperties(viewModel, customer);

		this.customerRepository.saveAndFlush(customer);
	}

}
