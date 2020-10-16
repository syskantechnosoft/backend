package com.demo.backend.viewmodels;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceViewModel {
	private int id;
	private LocalDateTime timestamp;
	private String shippingInformation;
	private String paymentTerms;

	private CustomerViewModel customer;

	private List<LineItemViewModel> lineItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getShippingInformation() {
		return shippingInformation;
	}

	public void setShippingInformation(String shippingInformation) {
		this.shippingInformation = shippingInformation;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public CustomerViewModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerViewModel customer) {
		this.customer = customer;
	}

	public List<LineItemViewModel> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemViewModel> lineItems) {
		this.lineItems = lineItems;
	}

}
