package com.prueba.tecnica.inventario.model.request;


import jakarta.annotation.Nullable;

public record ProductSearch(
        @Nullable String name,
        @Nullable String category,
        @Nullable Integer quantity,
        @Nullable Double price) {

    public void validate() {
        if (quantity != null && quantity < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (price != null && price <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
    }
}