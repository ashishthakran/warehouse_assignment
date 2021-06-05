package com.ikea.wms.api.dao.service;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.model.product.ProductDto;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public interface IProductDao {

    ProductDto save(ProductDto productDto);

    ProductDto get(BigInteger id);

    List<ProductDto> getAll();

    List<ProductDto> createProducts(List<ProductDto> productDtoList) throws ArticleNotFoundException, InsufficientInventoryException;

    ProductDto createProduct(ProductDto productDto) throws ArticleNotFoundException, InsufficientInventoryException;
}
