package com.prueba.tecnica.inventario.model.request;

import com.prueba.tecnica.inventario.constants.ValidationMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductDto(
    @NotBlank(message = ValidationMessages.REQUIRED_NAME) String name,
    @NotBlank(message = ValidationMessages.REQUIRED_CATEGORY) String category,
    @Min(value = 0, message = ValidationMessages.MIN_QUANTITY) int quantity,
    @Positive(message = ValidationMessages.POSITIVE_PRICE) double price) {}
