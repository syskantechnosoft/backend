package com.demo.backend.services.interfaces;

import java.util.List;

import com.demo.backend.viewmodels.CustomerCreateViewModel;
import com.demo.backend.viewmodels.CustomerViewModel;

public interface ICustomerService {
	List<CustomerViewModel> get();

	CustomerViewModel get(int id);

	void create(CustomerCreateViewModel viewModel);
}
