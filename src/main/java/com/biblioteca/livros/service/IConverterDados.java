package com.biblioteca.livros.service;

public interface IConverterDados {
    <T> T getData(String json, Class<T> classe);
}
