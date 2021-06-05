package com.ikea.wms.api.web.mapper;

import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.web.model.article.Article;

import javax.validation.Valid;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ArticleMapper {

    public static ArticleDto mapToDto(@Valid Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .name(article.getName())
                .stock(article.getStock())
                .build();
    }

    public static Article mapToModel(ArticleDto articleDto) {
        return Article.builder()
                .id(articleDto.getId())
                .name(articleDto.getName())
                .stock(articleDto.getStock())
                .build();
    }
}
