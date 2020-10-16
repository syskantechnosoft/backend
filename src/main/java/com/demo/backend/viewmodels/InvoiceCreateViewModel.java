package com.demo.backend.viewmodels;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceCreateViewModel {
	private int id;
	private LocalDateTime timestamp;
	private String shippingInformation;
	private String paymentTerms;

	private int customerId;

	private List<LineItemCreateViewModel> lineItems;

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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<LineItemCreateViewModel> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemCreateViewModel> lineItems) {
		this.lineItems = lineItems;
	}

}
