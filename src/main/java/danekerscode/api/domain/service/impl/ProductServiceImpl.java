package danekerscode.api.domain.service.impl;

import danekerscode.api.domain.dto.ProductDTO;
import danekerscode.api.common.exception.EntityNotFoundException;
import danekerscode.api.domain.mapper.ProductMapper;
import danekerscode.api.domain.model.Product;
import danekerscode.api.domain.repository.ProductRepository;
import danekerscode.api.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
    }
}
