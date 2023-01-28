package br.com.laborcode.angularcrud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.laborcode.angularcrud.model.entities.Products;
import br.com.laborcode.angularcrud.model.repositories.ProductsRepository;
import br.com.laborcode.angularcrud.utils.ResponseMessageSuccess;
import br.com.laborcode.angularcrud.utils.ResponseMessagesThrowable;
import br.com.laborcode.angularcrud.utils.exceptions.BadRequest;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	ProductsRepository productsRepository;
	
	@DeleteMapping("/json")
	ResponseMessageSuccess deleteProductJson(@RequestBody Products products) {
		ResponseMessagesThrowable messageError = new ResponseMessagesThrowable();

		if (products.getId() == null) {
			messageError.setMessage("os parametros de deleção estão inválidos e não podem ser nulos");
			throw new BadRequest(messageError);
		}

		int id = products.getId().intValue();
		Optional<Products> product = productsRepository.findById(id);

		if (!product.isPresent()) {
			messageError.setMessage("registro não encontrado");
			throw new BadRequest(messageError);
		}

		productsRepository.delete(products);
		ResponseMessageSuccess message = new ResponseMessageSuccess(
				"produto deletado com sucesso!", "DELETE", 200);
		return message;
	}

	@DeleteMapping
	ResponseMessageSuccess deleteProduct(Products products) {
		ResponseMessagesThrowable messageError = new ResponseMessagesThrowable();

		if (products.getId() == null) {
			messageError.setMessage("os parametros de deleção estão inválidos e não podem ser nulos");
			throw new BadRequest(messageError);
		}

		int id = products.getId().intValue();
		Optional<Products> product = productsRepository.findById(id);

		if (!product.isPresent()) {
			messageError.setMessage("registro não encontrado");
			throw new BadRequest(messageError);
		}

		productsRepository.delete(products);
		ResponseMessageSuccess message = new ResponseMessageSuccess(
				"objeto deletado com sucesso id: " + products.getId(), "DELETE", 200);
		return message;
	}

	@PutMapping("/json")
	Products updateUsingPath(@RequestBody Products products) {
		Optional<Products> validProduct = productsRepository.findById(products.getId().intValue());
		if (!validProduct.isPresent()) {
			ResponseMessagesThrowable message = new ResponseMessagesThrowable();
			message.setMessage("este produto não existe");
			throw new BadRequest(message);
		}

		Products product = productsRepository.save(products);
		return product;
	}

	@PutMapping
	Products updateProduct(Products products) {
		if (products.getId() == null || products.getName() == null || products.getPrice() <= 0) {
			ResponseMessagesThrowable message = new ResponseMessagesThrowable();
			message.setMessage("os parametros de alteração são inválidos e não podem ser nulos");
			throw new BadRequest(message);
		}

		productsRepository.save(products);
		return products;
	}

	@GetMapping
	@RequestMapping("/pagination")
	Iterable<Products> getProductsPaginate(@RequestParam(name = "page") int pageValue,
			@RequestParam(name = "size") int sizeValue) {
		Pageable page = PageRequest.of(pageValue, sizeValue);
		return productsRepository.findAll(page);
	}

	@GetMapping
	Iterable<Products> getProducts(Products products) {
		return productsRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Products> getById(@PathVariable int id) {
		return productsRepository.findById(id);
	}

	@PostMapping("/json")
	@ResponseStatus(HttpStatus.CREATED)
	Products newProductJson(@RequestBody Products products) {
		ResponseMessagesThrowable message = new ResponseMessagesThrowable();

		if (products.getPrice() < 0 || products.getPrice() == 0) {
			message.setMessage("o preço do produto não pode ser zero ou menor que zero");
			throw new BadRequest(message);
		} else if (products.getName().trim().equals("")) {
			message.setMessage("o nome do produto não pode ser em branco");
			throw new BadRequest(message);
		} else {
			productsRepository.save(products);
		}

		return products;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Products newProduct(Products products) {
		ResponseMessagesThrowable message = new ResponseMessagesThrowable();

		if (products.getPrice() < 0 || products.getPrice() == 0) {
			message.setMessage("o preço do produto não pode ser zero ou menor que zero");
			throw new BadRequest(message);
		} else if (products.getName().trim().equals("")) {
			message.setMessage("o nome do produto não pode ser em branco");
			throw new BadRequest(message);
		} else {
			productsRepository.save(products);
		}

		return products;
	}
}
