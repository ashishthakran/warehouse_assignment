package com.ikea.wms.api.service.validator;

import com.ikea.wms.api.core.exceptions.DuplicateArticleException;
import com.ikea.wms.api.core.model.article.ArticleDto;

import java.util.Optional;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ArticleValidator {

    private static final String ERR_DUPLICATE_ARTICLE = "Article with %d already exists.";

    public static void isDuplicate(Optional<ArticleDto> articleDto) throws DuplicateArticleException {
        if(articleDto.isPresent()) {
            throw new DuplicateArticleException(String.format(ERR_DUPLICATE_ARTICLE, articleDto.get().getId()));
        }
    }
}
