package com.ikea.wms.api.dao.mapper;

import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.dao.entity.article.Articles;
import com.ikea.wms.api.dao.entity.product.ProductArticleId;
import com.ikea.wms.api.dao.entity.product.ProductArticles;
import com.ikea.wms.api.dao.entity.product.Products;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ProductArticleMapper {

    public static ProductArticles mapToEntity(ArticleDto articleDto, ProductDto productDto) {
        return ProductArticles.builder()
                .id(buildId(buildProduct(productDto), buildArticle(articleDto)))
                .stock(articleDto.getStock())
                .build();
    }

    private static ProductArticleId buildId(Products product, Articles article) {
        return ProductArticleId.builder()
                .product(product)
                .article(article)
                .build();
    }

    private static Products buildProduct(ProductDto productDto) {
        return Products.builder()
                .id(productDto.getId())
                .build();
    }

    private static Articles buildArticle(ArticleDto articleDto) {
        return Articles.builder()
                .id(articleDto.getId())
                .build();
    }
}
