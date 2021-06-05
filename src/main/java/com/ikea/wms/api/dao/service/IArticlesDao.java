package com.ikea.wms.api.dao.service;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.model.article.ArticleDto;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public interface IArticlesDao {

    List<ArticleDto> saveAll(@Valid List<ArticleDto> articleDtoList);

    ArticleDto save(@Valid ArticleDto articleDto);

    List<ArticleDto> getAll();

    ArticleDto get(BigInteger id);

    ArticleDto updateInventory(BigInteger id, BigInteger soldInventory) throws ArticleNotFoundException, InsufficientInventoryException;
}
