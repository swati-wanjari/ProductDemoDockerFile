package com.smarbl.controller;

import com.smarbl.api.GenericResponse;
import com.smarbl.entity.Product;
import com.smarbl.exception.ResourceNotFoundException;
import com.smarbl.exception.ValidationException;
import com.smarbl.request.ProductRequest;
import com.smarbl.response.ProductResponse;
import com.smarbl.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @Operation(description = "add a Product", responses = {@ApiResponse(responseCode = "201", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),})
    public GenericResponse addProduct(@RequestBody ProductRequest productRequest) {
        GenericResponse genericResponse = new GenericResponse();
        ProductResponse productResponse = productService.createProduct(productRequest);
        genericResponse.setData(productResponse);
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setMessage("Product Added Successfully");
        return genericResponse;
    }

    @GetMapping("/{id}")
    @Operation(description = "get product by id", responses = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}), @ApiResponse(responseCode = "404")})
    public GenericResponse getProductById(@PathVariable int id) throws ValidationException {
        GenericResponse genericResponse = new GenericResponse();
        ProductResponse productResponse = productService.getProductById(id);
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setMessage("Product Found");
        genericResponse.setData(productResponse);
        return genericResponse;
    }

    @GetMapping("/search")
    @Operation(description = "Search Product by name or description[", responses = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Product.class)))})})
    public GenericResponse searchProductByNameOrDescription(@RequestParam String query, Pageable pageable) throws ValidationException {
        GenericResponse genericResponse = new GenericResponse();
        Page<Product> productResponsePage = productService.searchByNameOrDescription(query, pageable);
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setMessage("List of products matching the search criteria");
        genericResponse.setTotal(productResponsePage.getTotalElements());
        genericResponse.setTotalPages(productResponsePage.getTotalPages());
        genericResponse.setPageNum(productResponsePage.getNumber());
        genericResponse.setData(productResponsePage.getContent());
        return genericResponse;
    }

    @GetMapping
    @Operation(description = "get all products", responses = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Product.class)))})})
    public GenericResponse getAllProducts(Pageable pageable) {
        GenericResponse genericResponse = new GenericResponse();
        Page<ProductResponse> productResponsesPage = productService.getAllProductsPaged(pageable);
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setMessage("List of products");
        genericResponse.setTotal(productResponsesPage.getTotalElements());
        genericResponse.setTotalPages(productResponsesPage.getTotalPages());
        genericResponse.setPageNum(productResponsesPage.getNumber());
        genericResponse.setData(productResponsesPage.getContent());
        return genericResponse;
    }

    @PutMapping
    @Operation(description = "update a product", responses = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),})
    public GenericResponse updateProduct(@RequestBody ProductRequest productRequest) throws ValidationException {
        ProductResponse productResponse = productService.updateProduct(productRequest);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("product Updated successfully");
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(productResponse);
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    @Operation(description = "delete a product", responses = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),})
    public GenericResponse deleteProduct(@PathVariable int id) throws ResourceNotFoundException {
        GenericResponse genericResponse = new GenericResponse();
        boolean deletionStatus = productService.deleteProduct(id);
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setMessage("Product Deleted Successfully");
        return genericResponse;
    }
}

