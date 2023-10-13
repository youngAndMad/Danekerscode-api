package danekerscode.api.controller;

import danekerscode.api.dto.ProductDTO;
import danekerscode.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity
                .ok(productService.findAll());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    @RequestMapping(
            path = "{id}",
            method = {
                    RequestMethod.PUT,
                    RequestMethod.PATCH
            }
    )
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        return ResponseEntity.ok(productService.update(productDTO, id));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}
