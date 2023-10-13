package danekerscode.api.service.impl;

import danekerscode.api.dto.ProductDTO;
import danekerscode.api.exception.EntityNotFoundException;
import danekerscode.api.mapper.ProductMapper;
import danekerscode.api.model.Product;
import danekerscode.api.repository.ProductRepository;
import danekerscode.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product create(ProductDTO productDTO) {
        var product = productMapper.toModel(productDTO);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll(Sort.by("id"));
    }

    @Override
    public Product update(ProductDTO productDTO, Long id) {
        var product = this.findById(id);
        productMapper.update(product, productDTO);
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
    }
}
