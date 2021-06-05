package com.ikea.wms.api.service.impl;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.dao.service.IArticlesDao;
import com.ikea.wms.api.service.IArticleService;
import com.ikea.wms.api.service.validator.ArticleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Service
@Validated
@RequiredArgsConstructor
public class ArticleService implements IArticleService {

    private static final String ERR_ARTICLE_NOT_FOUND = "Article with id %d not found";

    private final IArticlesDao articlesDao;

    @Override
    public void saveAll(List<ArticleDto> articleDtoList) {
        articleDtoList.stream()
                .map(ArticleDto::getId)
                .filter(Objects::nonNull)
                .map(articlesDao::get)
                .filter(Objects::nonNull)
                .map(Optional::ofNullable)
                .forEach(articleDto -> ArticleValidator.isDuplicate(articleDto));
        articlesDao.saveAll(articleDtoList);
    }

    @Override
    public BigInteger save(ArticleDto articleDto) {
        Optional<ArticleDto> existingArticle = Optional.ofNullable(articleDto.getId())
                .map(articlesDao::get);
        ArticleValidator.isDuplicate(existingArticle);
        return articlesDao.save(articleDto).getId();
    }

    @Override
    public List<ArticleDto> getAll() {
        return articlesDao.getAll();
    }

    @Override
    public ArticleDto get(BigInteger id) throws ArticleNotFoundException {
        ArticleDto articleDto = articlesDao.get(id);
        if(null == articleDto) {
            throw new ArticleNotFoundException(String.format(ERR_ARTICLE_NOT_FOUND, id));
        }
        return articleDto;
    }
}
