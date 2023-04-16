package com.shahian.msproduct.service;

import com.shahian.msproduct.model.Product;
import com.shahian.msproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Product with id " + id + " not found"));
    }

    public List<Product> getProductByCategoryId(long id) {
        return productRepository.findByCategoryId(id);

    }
    public List<Product> getProductByOrderId(long id) {
        return productRepository.findByCategoryId(id);

    }
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Product with id " + id + " not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Product with id " + id + " not found"));
        productRepository.delete(product);
    }
}
