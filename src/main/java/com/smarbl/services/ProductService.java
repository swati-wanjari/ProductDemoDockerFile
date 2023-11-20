package com.smarbl.services;

import com.smarbl.dao.ProductRepo;
import com.smarbl.dto.DtoTransformer;
import com.smarbl.entity.Product;
import com.smarbl.exception.ResourceNotFoundException;
import com.smarbl.exception.ValidationException;
import com.smarbl.request.ProductRequest;
import com.smarbl.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DtoTransformer dtoTransformer;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = dtoTransformer.transformProductRequestToProduct(productRequest);
        Product productSaved = productRepo.save(product);
        return dtoTransformer.transformProductToResponse(productSaved);
    }

    public ProductResponse getProductById(int id) throws ValidationException {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            return dtoTransformer.transformProductToResponse(product.get());
        } else {
            throw new ValidationException("Product not found", "Product not found with id: " + id);
        }
    }

    public Page<Product> searchByNameOrDescription(String query, Pageable pageable) throws ValidationException {
        if (query != null && !query.isEmpty()) {
            return productRepo.findByNameOrDesc(query, pageable);
        } else {
            return Page.empty();
        }
    }

    public Page<ProductResponse> getAllProductsPaged(Pageable pageable) {
        return dtoTransformer.convertPageToProductResponse(productRepo.findAll(pageable));
    }

    public boolean deleteProduct(int productId) throws ResourceNotFoundException {
        if (productRepo.existsById(productId)) {
            productRepo.deleteById(productId);
            return true; // Deletion successful
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public ProductResponse updateProduct(ProductRequest productRequest) throws ValidationException {
        if (productRequest != null) {
            int id = productRequest.getId();
            if (productRepo.existsById(id)) {
                Product existingProduct = productRepo.findById(id).orElseThrow(() ->
                        new ValidationException("Product can't be updated", "Product not found with id: " + id)
                );
                existingProduct = dtoTransformer.transformProductRequestToProduct(productRequest);
                Product updatedProduct = productRepo.save(existingProduct);

                return dtoTransformer.transformProductToResponse(updatedProduct);
            } else {
                throw new ValidationException("Product can't be updated", "Product not found with id: " + id);
            }
        } else {
            throw new ValidationException("Product can't be updated", "Product cannot be null");
        }
    }


}
