package com.ikea.wms.api.service;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.DuplicateArticleException;
import com.ikea.wms.api.core.model.article.ArticleDto;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

public interface IArticleService {

    void saveAll(@Valid List<ArticleDto> articleDtoList) throws DuplicateArticleException;

    BigInteger save(@Valid ArticleDto article) throws DuplicateArticleException;

    List<ArticleDto> getAll();

    ArticleDto get(BigInteger id) throws ArticleNotFoundException;
}
