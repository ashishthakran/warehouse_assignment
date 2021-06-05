package com.ikea.wms.api.dao.service.impl;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.dao.service.IArticlesDao;
import com.ikea.wms.api.dao.entity.article.Articles;
import com.ikea.wms.api.dao.mapper.ArticleMapper;
import com.ikea.wms.api.dao.repository.ArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Service
@Validated
@RequiredArgsConstructor
class ArticlesDaoImpl implements IArticlesDao {

    private static final String ERR_ARTICLE_NOT_FOUND = "Article with id %d not found";
    private static final String ERR_ARTICLE_INSUFFICIENT_INVENTORY = "Article with id %d doesn't have enough stocks available. Article has %d available inventory.";

    private final ArticlesRepository articlesRepository;

    @Override
    @Transactional
    public List<ArticleDto> saveAll(List<ArticleDto> articleDtoList) {
        List<Articles> articlesList = articleDtoList.stream()
                .map(ArticleMapper::mapToEntity)
                .collect(Collectors.toList());
        articlesRepository.saveAll(articlesList);
        return articlesList.stream()
                .map(ArticleMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleDto save(ArticleDto articleDto) {
        Articles article = ArticleMapper.mapToEntity(articleDto);
        articlesRepository.save(article);
        return ArticleMapper.mapToDto(article);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDto> getAll() {
        List<Articles> articlesList = articlesRepository.findAll();
        return articlesList.stream()
                .map(ArticleMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDto get(BigInteger id) {
        return articlesRepository.findById(id)
                .map(ArticleMapper::mapToDto)
                .orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ArticleDto updateInventory(BigInteger id, BigInteger soldInventory) throws ArticleNotFoundException, InsufficientInventoryException {
        Articles article = articlesRepository.findById(id)
                .orElseThrow(() -> {throw new ArticleNotFoundException(String.format(ERR_ARTICLE_NOT_FOUND, id));});
        if(soldInventory.compareTo(article.getAvailableStock()) > 0)
            throw new InsufficientInventoryException(String.format(ERR_ARTICLE_INSUFFICIENT_INVENTORY, id, article.getAvailableStock()));
        article.setAvailableStock(article.getAvailableStock().subtract(soldInventory));
        return ArticleMapper.mapToDto(article);
    }
}
