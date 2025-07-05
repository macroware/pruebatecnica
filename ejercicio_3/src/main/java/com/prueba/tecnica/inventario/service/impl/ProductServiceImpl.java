package com.prueba.tecnica.inventario.service.impl;

import com.prueba.tecnica.inventario.collection.ProductDocument;
import com.prueba.tecnica.inventario.exception.ProductNotFoundException;
import com.prueba.tecnica.inventario.mapper.ProductMapper;
import com.prueba.tecnica.inventario.model.request.ProductDto;
import com.prueba.tecnica.inventario.model.request.ProductSearch;
import com.prueba.tecnica.inventario.repository.ProductRepository;
import com.prueba.tecnica.inventario.service.IProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final MongoTemplate mongoTemplate;

  @Override
  public List<ProductDocument> findAll() {
    return this.productRepository.findAll();
  }

  @Override
  public ProductDocument create(ProductDto productDto) {
    var productDocument = this.productMapper.toDocument(productDto);
    return this.productRepository.save(productDocument);
  }

  @Override
  public ProductDocument findById(String id) {
    return this.productRepository
        .findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con id: " + id));
  }

  @Override
  public List<ProductDocument> search(ProductSearch productSearch) {
    var criteria = new ArrayList<Criteria>();

    Optional.ofNullable(productSearch.name())
        .filter(s -> !s.isBlank())
        .ifPresent(name -> criteria.add(Criteria.where("name").is(name)));

    Optional.ofNullable(productSearch.category())
        .filter(s -> !s.isBlank())
        .ifPresent(category -> criteria.add(Criteria.where("category").is(category)));

    Optional.ofNullable(productSearch.quantity())
        .filter(q -> q > 0)
        .ifPresent(quantity -> criteria.add(Criteria.where("quantity").is(quantity)));

    Optional.ofNullable(productSearch.price())
        .filter(p -> p > 0)
        .ifPresent(price -> criteria.add(Criteria.where("price").is(price)));

    Query query = new Query();
    if (!criteria.isEmpty()) {
      query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
    }

    return mongoTemplate.find(query, ProductDocument.class);
  }

  @Override
  public ProductDocument update(String id, ProductDto productDto) {
    var productToUpdate = this.findById(id);
    this.productMapper.updateDocumentFromDto(productDto, productToUpdate);
    return this.productRepository.save(productToUpdate);
  }

  @Override
  public void delete(String id) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundException("Producto no encontrado con id: " + id);
    }
    this.productRepository.deleteById(id);
  }
}
