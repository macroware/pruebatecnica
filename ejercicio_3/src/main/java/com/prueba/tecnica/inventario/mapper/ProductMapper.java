package com.prueba.tecnica.inventario.mapper;

import com.prueba.tecnica.inventario.collection.ProductDocument;
import com.prueba.tecnica.inventario.model.request.ProductDto;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductDocument toDocument(ProductDto dto);

  ProductDto toDto(ProductDocument document);

  List<ProductDto> toDtoList(List<ProductDocument> collection);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateDocumentFromDto(ProductDto dto, @MappingTarget ProductDocument document);
}
