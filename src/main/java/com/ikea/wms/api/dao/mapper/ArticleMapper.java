package com.ikea.wms.api.dao.mapper;

import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.dao.entity.article.Articles;
import com.ikea.wms.api.dao.entity.product.ProductArticles;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ArticleMapper {

    public static Articles mapToEntity(ArticleDto articleDto) {
        return Articles.builder()
                .id(articleDto.getId())
                .name(articleDto.getName())
                .availableStock(articleDto.getStock())
                .build();
    }

    public static ArticleDto mapToDto(Articles article) {
        return ArticleDto.builder()
                .id(article.getId())
                .name(article.getName())
                .stock(article.getAvailableStock())
                .build();
    }

    public static ArticleDto mapToDto(ProductArticles productArticle) {
        return ArticleDto.builder()
                .id(productArticle.getId().getArticle().getId())
                .name(productArticle.getId().getArticle().getName())
                .stock(productArticle.getId().getArticle().getAvailableStock())
                .build();
    }
}
