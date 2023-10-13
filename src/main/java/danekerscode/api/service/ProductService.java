package danekerscode.api.service;

import danekerscode.api.dto.ProductDTO;
import danekerscode.api.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    void delete(Long id);

    List<Product> findAll();

    Product create(ProductDTO productDTO);

    Product update(ProductDTO productDTO, Long id);

}
