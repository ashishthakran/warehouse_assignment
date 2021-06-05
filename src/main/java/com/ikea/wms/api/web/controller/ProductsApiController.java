package com.ikea.wms.api.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.service.IProductService;
import com.ikea.wms.api.web.exceptions.ApiException;
import com.ikea.wms.api.web.mapper.ProductMapper;
import com.ikea.wms.api.web.model.ApiResponse;
import com.ikea.wms.api.web.model.product.Product;
import com.ikea.wms.api.web.model.product.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsApiController {

    private static final String MSG_PRODUCTS_UPLOADED = "Products created successfully.";
    private static final String MSG_PRODUCT_CREATED = "Product created successfully.";

    private static ObjectMapper MAPPER = new ObjectMapper();

    private final IProductService productService;

    @PostMapping(value = "/upload", produces = APPLICATION_JSON_VALUE, consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadProducts(@RequestParam("file") MultipartFile file) {
        try {
            ProductRequest productRequest = MAPPER.readValue(file.getBytes(), ProductRequest.class);
            List<ProductDto> articleDtoList = productRequest.getProducts().stream()
                    .map(ProductMapper::mapToDto)
                    .collect(Collectors.toList());
            productService.createProducts(articleDtoList);
        } catch (IOException ex) {
            throw new ApiException(String.format("Unable to read data from ", file.getName()));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .status(true)
                        .message(MSG_PRODUCTS_UPLOADED)
                        .build());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product product) {
        productService.createProduct(ProductMapper.mapToDto(product));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .status(true)
                        .message(MSG_PRODUCT_CREATED)
                        .build());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<Product>>> listProducts() {
        List<Product> productList = productService.getAll().stream()
                .map(ProductMapper::mapToModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(ApiResponse.<List<Product>>builder()
                        .status(true)
                        .data(productList)
                        .build());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable("id") BigInteger id) {
        Product product = ProductMapper.mapToModel(productService.get(id));
        return ResponseEntity.ok()
                .body(ApiResponse.<Product>builder()
                        .status(true)
                        .data(product)
                        .build());
    }
}
