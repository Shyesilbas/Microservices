package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.Model.Product;
import org.example.productservice.dto.ProductList;
import org.example.productservice.dto.ProductRequest;
import org.example.productservice.dto.ProductResponse;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductList> getProducts(){
 return productRepository.findAll()
         .stream()
         .map(product -> new ProductList(product.getId(), product.getName(),product.getDescription(),product.getPrice()))
         .toList();
    }

    public ProductResponse saveProduct(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product Saved Successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public void deleteProduct(String id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
           productRepository.deleteById(id);
           log.info("Product Deleted Successfully");
        }else{
            throw new IllegalStateException("Product Not found");
        }
    }

}
