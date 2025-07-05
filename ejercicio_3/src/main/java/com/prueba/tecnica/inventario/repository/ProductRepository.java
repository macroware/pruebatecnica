package com.prueba.tecnica.inventario.repository;

import com.prueba.tecnica.inventario.collection.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument, String> {}
