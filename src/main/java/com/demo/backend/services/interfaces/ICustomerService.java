package com.demo.backend.services.interfaces;

import java.util.List;

import com.demo.backend.viewmodels.CustomerCreateViewModel;
import com.demo.backend.viewmodels.CustomerLookupViewModel;
import com.demo.backend.viewmodels.CustomerViewModel;

public interface ICustomerService {
	List<CustomerViewModel> get();

	List<CustomerLookupViewModel> getLookup();

	CustomerViewModel get(int id);

	void create(CustomerCreateViewModel viewModel);
}
