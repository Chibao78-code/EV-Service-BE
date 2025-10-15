// java
    package com.swp.evmsystem.controller;

    import com.swp.evmsystem.dto.request.CreateCustomerRequest;
    import com.swp.evmsystem.dto.request.UpdateCustomerRequest;
    import com.swp.evmsystem.dto.response.CustomerDTO;
    import com.swp.evmsystem.service.CustomerService;
    import jakarta.validation.Valid;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.util.UriComponentsBuilder;

    import java.net.URI;
    import java.util.List;

    @RestController
    @RequestMapping("/api/customer")
    public class CustomerController {
        private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
            this.customerService = customerService;
        }

        @GetMapping
        public ResponseEntity<List<CustomerDTO>> getAll() {
            List<CustomerDTO> customers = customerService.findAll();
            return ResponseEntity.ok(customers);
        }

        @GetMapping("/{id}")
        public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
            return customerService.findById(id)
                    .map(c -> ResponseEntity.ok(c))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<CustomerDTO> create(
                @Valid @RequestBody CreateCustomerRequest request,
                UriComponentsBuilder uriBuilder) {
            CustomerDTO created = customerService.create(request);
            URI location = uriBuilder.path("/api/customer/{id}").buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(location).body(created);
        }

        @PutMapping("/{id}")
        public ResponseEntity<CustomerDTO> update(
                @PathVariable Long id,
                @Valid @RequestBody UpdateCustomerRequest request) {
            return customerService.update(id, request)
                    .map(c -> ResponseEntity.ok(c))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            boolean removed = customerService.delete(id);
            return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
    }