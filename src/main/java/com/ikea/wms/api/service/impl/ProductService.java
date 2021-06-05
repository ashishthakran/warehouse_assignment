package com.ikea.wms.api.service.impl;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.exceptions.ProductNotFoundException;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.dao.service.IProductDao;
import com.ikea.wms.api.service.IProductService;
import com.ikea.wms.api.service.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private static final String ERR_PRODUCT_NOT_FOUND = "Product with id %d not found.";

    private final IProductDao productDao;

    @Override
    public ProductDto get(BigInteger id) throws ProductNotFoundException {
        ProductDto productDto = productDao.get(id);
        if(null == productDto) {
            throw new ProductNotFoundException(String.format(ERR_PRODUCT_NOT_FOUND, id));
        }
        return productDto;
    }

    @Override
    public List<ProductDto> getAll() {
        return productDao.getAll();
    }

    @Override
    public void createProducts(List<ProductDto> productDtoList) throws ArticleNotFoundException, InsufficientInventoryException {
        productDtoList.stream()
                .map(ProductDto::getId)
                .filter(Objects::nonNull)
                .map(productDao::get)
                .filter(Objects::nonNull)
                .map(Optional::ofNullable)
                .forEach(productDto -> ProductValidator.isDuplicate(productDto));
        productDao.createProducts(productDtoList);
    }

    @Override
    public BigInteger createProduct(ProductDto productDto) throws ArticleNotFoundException, InsufficientInventoryException {
        Optional<ProductDto> existingArticle = Optional.ofNullable(productDto.getId())
                .map(productDao::get);
        ProductValidator.isDuplicate(existingArticle);
        return productDao.createProduct(productDto).getId();
    }
}
