package com.ikea.wms.api.dao.mapper;

import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.dao.entity.product.Products;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ProductMapper {

    public static Products mapToEntity(ProductDto productDto) {
        Products product = Products.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
        return product;
    }

    public static ProductDto mapToDto(Products products) {
        ProductDto product = ProductDto.builder()
                .id(products.getId())
                .name(products.getName())
                .price(products.getPrice())
                .build();

        Set<ArticleDto> productArticlesDtos = Optional.ofNullable(products.getArticles())
                .orElse(Collections.emptySet()).stream()
                .map(productArticle -> ArticleMapper.mapToDto(productArticle))
                .collect(Collectors.toSet());

        return product.toBuilder().articles(productArticlesDtos).build();
    }
}
