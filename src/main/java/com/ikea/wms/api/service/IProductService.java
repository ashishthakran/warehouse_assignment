package com.ikea.wms.api.service;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.DuplicateProductException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.exceptions.ProductNotFoundException;
import com.ikea.wms.api.core.model.product.ProductDto;

import java.math.BigInteger;
import java.util.List;

public interface IProductService {

    ProductDto get(BigInteger id) throws ProductNotFoundException;

    List<ProductDto> getAll();

    void createProducts(List<ProductDto> productDtoList) throws ArticleNotFoundException, InsufficientInventoryException, DuplicateProductException;

    BigInteger createProduct(ProductDto productDto) throws ArticleNotFoundException, InsufficientInventoryException, DuplicateProductException;
}
