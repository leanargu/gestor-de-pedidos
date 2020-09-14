package com.example.gestordepedidos.repository;

public interface BumexMemcached {

    void set(String key, Object value);
    Object get(String key);
    void delete(String key);

}
