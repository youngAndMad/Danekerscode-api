package danekerscode.api.domain.service;

import danekerscode.api.domain.dto.ProductDTO;
import danekerscode.api.domain.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This interface provides methods for managing products.
 */
public interface ProductService {

    /**
     * Retrieves the product with the specified ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID
     */
    Product findById(Long id);

    /**
     * Deletes the product with the specified ID.
     *
     * @param id the ID of the product to delete
     */
    void delete(Long id);

    /**
     * Retrieves a list of all products.
     *
     * @return a list of all products
     */
    List<Product> findAll();

    /**
     * Creates a new product with the given product DTO.
     *
     * @param productDTO the product DTO containing the product details
     * @return the created product
     */
    Product create(ProductDTO productDTO);

    /**
     * Updates the product with the specified ID using the details from the product DTO.
     *
     * @param productDTO the product DTO containing the updated product details
     * @param id         the ID of the product to update
     * @return the updated product
     */
    Product update(ProductDTO productDTO, Long id);


    Page<Product> findAll(int page, int size);

}