package com.prueba.tecnica.inventario.model.request;

import jakarta.annotation.Nullable;

public record ProductUpdateDto(
        @Nullable String name,
        @Nullable String category,
        @Nullable Integer quantity,
        @Nullable Double price) {}
