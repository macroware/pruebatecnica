package com.prueba.tecnica.inventario.model.response;

public record ApiResponse<T>(int statusCode, String message, T response) {}
