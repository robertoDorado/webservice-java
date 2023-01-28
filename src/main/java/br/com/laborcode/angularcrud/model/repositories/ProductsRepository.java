package br.com.laborcode.angularcrud.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.laborcode.angularcrud.model.entities.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer>, PagingAndSortingRepository<Products, Integer> {
	
}
