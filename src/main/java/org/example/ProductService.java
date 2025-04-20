package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CacheEvict(value="allProductCache",allEntries = true)
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Cacheable(value = "allProductCache")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Cacheable(value="productCache",key="#id")
    public Product getProductById(String id){
        return productRepository.findById(id).orElse(null);
    }
}
