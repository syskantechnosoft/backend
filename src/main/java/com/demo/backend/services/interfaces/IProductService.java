package com.demo.backend.services.interfaces;

import java.util.List;

import com.demo.backend.viewmodels.ProductCreateViewModel;
import com.demo.backend.viewmodels.ProductLookupViewModel;
import com.demo.backend.viewmodels.ProductViewModel;

public interface IProductService {
	List<ProductViewModel> get();

	ProductViewModel get(int id);

	List<ProductLookupViewModel> getLookup();

	void create(ProductCreateViewModel viewModel);
}
