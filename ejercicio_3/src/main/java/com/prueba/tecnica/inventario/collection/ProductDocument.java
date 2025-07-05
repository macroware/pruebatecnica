package com.prueba.tecnica.inventario.collection;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "products")
public class ProductDocument {

  @Id private String id;

  @NotBlank(message = "El nombre del producto es requerido")
  private String name;

  @NotBlank(message = "La categoría del producto es requerida")
  private String category;

  @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
  private int quantity;

  @Positive(message = "El precio debe ser mayor a cero")
  private double price;
}
