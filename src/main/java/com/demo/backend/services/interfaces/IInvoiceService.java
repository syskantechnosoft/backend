package com.demo.backend.services.interfaces;

import java.util.List;

import com.demo.backend.viewmodels.InvoiceCreateViewModel;
import com.demo.backend.viewmodels.InvoiceListViewModel;
import com.demo.backend.viewmodels.InvoiceViewModel;

public interface IInvoiceService {
	List<InvoiceListViewModel> get();

	InvoiceViewModel get(int id);

	void create(InvoiceCreateViewModel viewModel);

}
