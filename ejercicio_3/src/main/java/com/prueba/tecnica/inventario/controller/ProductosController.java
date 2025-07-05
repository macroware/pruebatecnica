package com.prueba.tecnica.inventario.controller;

import com.prueba.tecnica.inventario.mapper.ProductMapper;
import com.prueba.tecnica.inventario.model.request.ProductDto;
import com.prueba.tecnica.inventario.model.request.ProductSearch;
import com.prueba.tecnica.inventario.model.response.ApiResponse;
import com.prueba.tecnica.inventario.service.IProductService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
@Validated
public class ProductosController {

  private final IProductService productService;
  private final ProductMapper productMapper;

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductDto>>> findAll() {
    var products = this.productMapper.toDtoList(this.productService.findAll());
    return ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), "Lista de productos", products));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ProductDto>> create(@RequestBody @Valid ProductDto productDto) {
    var createdDocument = this.productService.create(productDto);
    var newProduct = this.productMapper.toDto(createdDocument);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdDocument.getId())
            .toUri();
    return ResponseEntity.created(location)
        .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Producto creado", newProduct));
  }

  @GetMapping("/{id:[a-fA-F0-9]{24}}")
  public ResponseEntity<ApiResponse<ProductDto>> findById(@PathVariable String id) {
    var productDocument = this.productService.findById(id);
    var productFound = this.productMapper.toDto(productDocument);
    return ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), "Producto encontrado", productFound));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponse<List<ProductDto>>> search(
      @Valid @ModelAttribute ProductSearch productSearch) {
    productSearch.validate();
    var productCollection = this.productService.search(productSearch);
    var products = productMapper.toDtoList(productCollection);
    return ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), "Resultado de búsqueda", products));
  }

  @PutMapping("/{id:[a-fA-F0-9]{24}}")
  public ResponseEntity<ApiResponse<ProductDto>> update(
      @PathVariable String id, @RequestBody @Valid ProductDto productDto) {
    var documentUpdated = this.productService.update(id, productDto);
    var productUpdated = this.productMapper.toDto(documentUpdated);
    return ResponseEntity.ok(
        new ApiResponse<>(HttpStatus.OK.value(), "Producto actualizado", productUpdated));
  }

  @DeleteMapping("/{id:[a-fA-F0-9]{24}}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
    this.productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
