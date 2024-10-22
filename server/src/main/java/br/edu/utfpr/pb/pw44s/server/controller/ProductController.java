package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.ProductDTO;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.service.IProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private static IProductService productService;
    private static ModelMapper modelMapper;

    public ProductController(IProductService productService, ModelMapper modelMapper) {
        ProductController.productService = productService;
        ProductController.modelMapper = modelMapper;
    }

    @PostMapping // http://localhost:8025/categories
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO product) {
        Product productEntity = modelMapper.map(product, Product.class);
        productService.save(productEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(productEntity);
    }

    @PutMapping("{id}") //http://localhost:8025/categories/{id} em que {id} = um long
    public ResponseEntity<Product> update(@RequestBody @Valid Product product,
                                          @PathVariable Long id) {
        Product productUpdate = productService.update(id);
        return ResponseEntity.ok(productUpdate);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.findOne(id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }
}
