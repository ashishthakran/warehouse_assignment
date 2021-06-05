package com.ikea.wms.api.web.mapper;

import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.web.model.product.Product;
import com.ikea.wms.api.web.model.product.ProductArticle;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ProductMapper {

    public static ProductDto mapToDto(@Valid Product product) {
        Set<ArticleDto> articleDtoSet = product.getArticles().stream()
                .map(ProductMapper::buildArticle)
                .collect(Collectors.toSet());

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .articles(articleDtoSet)
                .build();
    }

    public static Product mapToModel(ProductDto productDto) {
        Set<ProductArticle> productArticleList = productDto.getArticles().stream()
                .map(ProductMapper::buildArticle)
                .collect(Collectors.toSet());

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .articles(productArticleList)
                .build();
    }

    private static ArticleDto buildArticle(ProductArticle productArticle) {
        return ArticleDto.builder()
                .id(productArticle.getId())
                .stock(productArticle.getStock())
                .build();
    }

    private static ProductArticle buildArticle(ArticleDto articleDto) {
        return ProductArticle.builder()
                .id(articleDto.getId())
                .stock(articleDto.getStock())
                .build();
    }
}
