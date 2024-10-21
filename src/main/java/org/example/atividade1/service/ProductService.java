package org.example.atividade1.service;
import lombok.RequiredArgsConstructor;
import org.example.atividade1.exception.ProductNotFoundException;
import org.example.atividade1.model.entity.Product;
import org.example.atividade1.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public void save(Product produto) {
        repository.save(produto);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> listAll(){
        return repository.findAll();
    }

    public void remove(Long id){
        repository.deleteById(id);
    }

    public void update(Product produto){
        repository.save(produto);
    }
}
