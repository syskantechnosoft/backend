package com.demo.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.demo.backend.exceptions.RecordNotFoundException;
import com.demo.backend.models.Invoice;
import com.demo.backend.models.LineItem;
import com.demo.backend.repositories.InvoiceRepository;
import com.demo.backend.services.interfaces.IInvoiceService;
import com.demo.backend.viewmodels.CustomerViewModel;
import com.demo.backend.viewmodels.InvoiceCreateViewModel;
import com.demo.backend.viewmodels.InvoiceListViewModel;
import com.demo.backend.viewmodels.InvoiceViewModel;
import com.demo.backend.viewmodels.LineItemViewModel;

public class InvoiceService implements IInvoiceService {
	private final InvoiceRepository invoiceRepository;

	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public List<InvoiceListViewModel> get() {
		return this.invoiceRepository.findAll().stream().map(i -> {
			InvoiceListViewModel viewModel = new InvoiceListViewModel();
			BeanUtils.copyProperties(i, viewModel);
			viewModel.setCustomerName(i.getCustomer().getName());
			return viewModel;
		}).collect(Collectors.toList());
	}

	@Override
	public InvoiceViewModel get(int id) {
		Optional<Invoice> invoiceDb = this.invoiceRepository.findById(id);

		if (invoiceDb.isEmpty()) {
			throw new RecordNotFoundException("Could not find invoice");
		}

		Invoice invoice = invoiceDb.get();
		InvoiceViewModel viewModel = new InvoiceViewModel();

		viewModel.setId(invoice.getId());
		viewModel.setPaymentTerms(invoice.getPaymentTerms());
		viewModel.setShippingInformation(invoice.getShippingInformation());
		viewModel.setTimestamp(invoice.getTimestamp());

		CustomerViewModel customerViewModel = new CustomerViewModel();
		BeanUtils.copyProperties(invoice.getCustomer(), customerViewModel);

		viewModel.setCustomer(customerViewModel);

		List<LineItemViewModel> lineItems = invoice.getLineItems().stream().map(li -> {
			LineItemViewModel lineItemViewModel = new LineItemViewModel();
			BeanUtils.copyProperties(li, lineItemViewModel);
			lineItemViewModel.setProductName(li.getProduct().getName());
			return lineItemViewModel;
		}).collect(Collectors.toList());

		viewModel.setLineItems(lineItems);

		return viewModel;
	}

	@Override
	public void create(InvoiceCreateViewModel viewModel) {
		Invoice invoice = new Invoice();

		invoice.setCustomerId(viewModel.getCustomerId());
		invoice.setPaymentTerms(viewModel.getPaymentTerms());
		invoice.setShippingInformation(viewModel.getShippingInformation());
		invoice.setTimestamp(viewModel.getTimestamp());

		Set<LineItem> lineItems = viewModel.getLineItems().stream().map(vm -> {
			LineItem lineItem = new LineItem();
			BeanUtils.copyProperties(vm, lineItem);
			return lineItem;
		}).collect(Collectors.toSet());

		invoice.setLineItems(lineItems);

		this.invoiceRepository.saveAndFlush(invoice);
	}

}
