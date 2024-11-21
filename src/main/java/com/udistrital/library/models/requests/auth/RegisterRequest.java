package com.udistrital.library.models.requests.auth;

public record RegisterRequest(String name, String email, String password, String Phone, Short role) {}
