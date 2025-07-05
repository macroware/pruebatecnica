package com.prueba.tecnica.inventario.service;

import com.prueba.tecnica.inventario.collection.ProductDocument;
import com.prueba.tecnica.inventario.model.request.ProductDto;
import com.prueba.tecnica.inventario.model.request.ProductSearch;
import java.util.List;

public interface IProductService {

  List<ProductDocument> findAll();

  ProductDocument create(ProductDto productDto);

  ProductDocument findById(String id);

  List<ProductDocument> search(ProductSearch productSearch);

  ProductDocument update(String id, ProductDto productDto);

  void delete(String id);
}
