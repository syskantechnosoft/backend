package com.demo.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.demo.backend.exceptions.RecordNotFoundException;
import com.demo.backend.models.Product;
import com.demo.backend.repositories.ProductRepository;
import com.demo.backend.services.interfaces.IProductService;
import com.demo.backend.viewmodels.ProductCreateViewModel;
import com.demo.backend.viewmodels.ProductLookupViewModel;
import com.demo.backend.viewmodels.ProductViewModel;

public class ProductService implements IProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductViewModel> get() {
		return this.productRepository.findAll().stream().map(c -> {
			ProductViewModel viewModel = new ProductViewModel();
			BeanUtils.copyProperties(c, viewModel);
			return viewModel;
		}).collect(Collectors.toList());
	}

	@Override
	public ProductViewModel get(int id) {
		Optional<Product> productDb = this.productRepository.findById(id);

		if (productDb.isEmpty()) {
			throw new RecordNotFoundException("Not found");
		}

		ProductViewModel viewModel = new ProductViewModel();
		BeanUtils.copyProperties(productDb.get(), viewModel);
		return viewModel;
	}

	@Override
	public List<ProductLookupViewModel> getLookup() {
		return this.productRepository.findAll().stream().map(c -> {
			ProductLookupViewModel viewModel = new ProductLookupViewModel();
			BeanUtils.copyProperties(c, viewModel);
			return viewModel;
		}).collect(Collectors.toList());
	}

	@Override
	public void create(ProductCreateViewModel viewModel) {
		Product product = new Product();
		BeanUtils.copyProperties(viewModel, product);

		this.productRepository.saveAndFlush(product);
	}
}
