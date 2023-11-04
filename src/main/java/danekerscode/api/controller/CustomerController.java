package danekerscode.api.controller;

import danekerscode.api.common.annotation.ValidatedMethod;
import danekerscode.api.domain.dto.CustomerDTO;
import danekerscode.api.domain.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    ResponseEntity<?> findAll() {
        return ResponseEntity
                .ok(customerService.findAll());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @PathVariable Long id
    ) {
        customerService.delete(id);
    }

    @PostMapping
    @ValidatedMethod
    ResponseEntity<?> create(
            @RequestBody @Valid CustomerDTO dto,
            BindingResult br
    ) {
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(customerService.create(dto));
    }

    @RequestMapping(
            path = "{id}",
            method = {RequestMethod.PUT, RequestMethod.PATCH}
    )
    ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO
    ) {
        return ResponseEntity
                .ok(customerService.update(customerDTO, id));
    }

    @GetMapping("{id}")
    ResponseEntity<?> find(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .ok(customerService.findById(id));
    }

}
