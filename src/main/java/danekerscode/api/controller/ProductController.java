package danekerscode.api.controller;

import danekerscode.api.common.annotation.ValidatedMethod;
import danekerscode.api.domain.dto.ProductDTO;
import danekerscode.api.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<?> findAll() {
        return ResponseEntity
                .ok(productService.findAll());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("pagination")
    ResponseEntity<?> findAll(
            @RequestParam int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity
                .ok(productService.findAll(page, size));
    }

    @PostMapping
    @ValidatedMethod
    ResponseEntity<?> create(
            @RequestBody @Valid ProductDTO productDTO,
            BindingResult br

    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(productDTO));
    }

    @RequestMapping(
            path = "{id}",
            method = {
                    RequestMethod.PUT,
                    RequestMethod.PATCH
            }
    )
    ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        return ResponseEntity.ok(productService.update(productDTO, id));
    }

    @GetMapping("{id}")
    ResponseEntity<?> find(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .ok(productService.findById(id));
    }
}
