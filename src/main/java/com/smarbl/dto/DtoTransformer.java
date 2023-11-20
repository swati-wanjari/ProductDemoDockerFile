package com.smarbl.dto;

import com.smarbl.entity.Product;
import com.smarbl.request.ProductRequest;
import com.smarbl.response.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DtoTransformer {


    public Product transformProductRequestToProduct(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        return product;
    }

    public ProductResponse transformProductToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    public Page<ProductResponse> convertPageToProductResponse(Page<Product> productPage) {
        return productPage.map(this::transformProductToResponse);
    }


}

