package com.ikea.wms.api.dao.service.impl;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.dao.entity.article.Articles;
import com.ikea.wms.api.dao.repository.ArticlesRepository;
import com.ikea.wms.api.dao.service.IArticlesDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ArticlesDaoTest {

    private final ArticlesRepository articlesRepository = mock(ArticlesRepository.class);
    private final IArticlesDao articlesDao = new ArticlesDaoImpl(articlesRepository);

    @Test
    public void testUpdateInventory_ArticleNotFound() {
        //GIVEN
        BigInteger id = BigInteger.ZERO;
        BigInteger inventory = BigInteger.ONE;

        //WHEN
        Mockito.when(articlesRepository.findById(id)).thenThrow(ArticleNotFoundException.class);

        Throwable throwable =   assertThrows(ArticleNotFoundException.class,
                () -> articlesDao.updateInventory(id, inventory));

        //THEN
        assertThat(throwable).isNotNull();
    }

    @Test
    public void testUpdateInventory_InsufficientInventory() {
        //GIVEN
        BigInteger id = BigInteger.ZERO;
        BigInteger inventory = BigInteger.TEN;

        //WHEN
        Mockito.when(articlesRepository.findById(id)).thenReturn(Optional.of(createArticle(BigInteger.ONE)));

        Throwable throwable =   assertThrows(InsufficientInventoryException.class,
                () -> articlesDao.updateInventory(id, inventory));

        //THEN
        assertThat(throwable).isNotNull();
    }

    @Test
    public void testUpdateInventory() {
        //GIVEN
        BigInteger id = BigInteger.ZERO;
        BigInteger inventory = BigInteger.ONE;

        //WHEN
        Mockito.when(articlesRepository.findById(id)).thenReturn(Optional.of(createArticle(BigInteger.TEN)));

        ArticleDto articleDto = articlesDao.updateInventory(id, inventory);

        //THEN
        assertThat(articleDto).isNotNull()
        .hasFieldOrPropertyWithValue("stock", BigInteger.valueOf(9l));
    }

    private Articles createArticle(BigInteger inventory) {
        return Articles.builder()
                .id(BigInteger.ONE)
                .availableStock(inventory)
                .name("Test Article")
                .build();
    }
}
