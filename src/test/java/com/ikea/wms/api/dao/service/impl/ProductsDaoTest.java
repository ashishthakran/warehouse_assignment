package com.ikea.wms.api.dao.service.impl;

import com.google.common.collect.Sets;
import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.dao.entity.article.Articles;
import com.ikea.wms.api.dao.entity.product.ProductArticleId;
import com.ikea.wms.api.dao.entity.product.ProductArticles;
import com.ikea.wms.api.dao.entity.product.Products;
import com.ikea.wms.api.dao.repository.ProductArticlesRepository;
import com.ikea.wms.api.dao.repository.ProductsRepository;
import com.ikea.wms.api.dao.service.IArticlesDao;
import com.ikea.wms.api.dao.service.IProductDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ProductsDaoTest {

    private final ProductsRepository productsRepository = mock(ProductsRepository.class);
    private final IArticlesDao articlesDao = mock(ArticlesDaoImpl.class);
    private final ProductArticlesRepository productArticlesRepository = mock(ProductArticlesRepository.class);

    private final IProductDao productDao = new ProductsDaoImpl(productsRepository, articlesDao, productArticlesRepository);

    @Test
    public void testCreateProduct_ArticleNotFound() {
        //GIVEN
        ProductDto productDto = createProductDto(Sets.newHashSet(createArticleDto(BigInteger.valueOf(10l))));
        Products product = createProduct();

        //WHEN
        Mockito.when(productsRepository.save(product)).thenReturn(createProduct());
        Mockito.when(articlesDao.updateInventory(any(), any())).thenThrow(ArticleNotFoundException.class);

        Throwable throwable =   assertThrows(ArticleNotFoundException.class,
                () -> productDao.createProduct(productDto));

        //THEN
        assertThat(throwable).isNotNull();
    }

    @Test
    public void testCreateProduct_InsufficientInventory() {
        //GIVEN
        ProductDto productDto = createProductDto(Sets.newHashSet(createArticleDto(BigInteger.valueOf(10l))));
        Products product = createProduct();

        //WHEN
        Mockito.when(productsRepository.save(product)).thenReturn(createProduct());
        Mockito.when(articlesDao.updateInventory(any(), any())).thenThrow(InsufficientInventoryException.class);

        Throwable throwable =   assertThrows(InsufficientInventoryException.class,
                () -> productDao.createProduct(productDto));

        //THEN
        assertThat(throwable).isNotNull();
    }

    @Test
    public void testCreateProduct() {
        //GIVEN
        ProductDto productDto = createProductDto(Sets.newHashSet(createArticleDto(BigInteger.valueOf(1l))));
        Products product = createProduct();
        ArticleDto articleDto = createArticleDto(BigInteger.valueOf(9l));
        Products savedProduct = createProductWithArticles(Sets.newHashSet(createProductArticles(BigInteger.valueOf(9l))));

        //WHEN
        Mockito.when(productsRepository.save(product)).thenReturn(product);
        Mockito.when(articlesDao.updateInventory(BigInteger.ONE, BigInteger.ONE)).thenReturn(articleDto);
        Mockito.when(productsRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(savedProduct));

         ProductDto returnedProduct = productDao.createProduct(productDto);

        //THEN
        assertThat(returnedProduct).isNotNull();
    }

    private ProductDto createProductDto(Set<ArticleDto> articles) {
        return ProductDto.builder()
                .name("Dining Chair")
                .price(BigDecimal.valueOf(12.33d))
                .articles(articles)
                .id(BigInteger.ONE)
                .build();
    }

    private ArticleDto createArticleDto(BigInteger inventory) {
        return ArticleDto.builder()
                .id(BigInteger.ONE)
                .stock(inventory)
                .name("Test Article")
                .build();
    }

    private Products createProduct() {
        return Products.builder()
                .id(BigInteger.ONE)
                .name("Dining Table")
                .price(BigDecimal.valueOf(12.33d))
                .build();
    }

    private Products createProductWithArticles(Set<ProductArticles> articles) {
        return Products.builder()
                .id(BigInteger.ONE)
                .name("Dining Table")
                .price(BigDecimal.valueOf(12.33d))
                .articles(articles)
                .build();
    }

    private Articles createArticle() {
        return Articles.builder()
                .id(BigInteger.ONE)
                .name("leg")
                .availableStock(BigInteger.valueOf(10l))
                .build();
    }

    private ProductArticles createProductArticles(BigInteger stock) {
        ProductArticleId id = ProductArticleId.builder()
                .product(createProduct())
                .article(createArticle())
                .build();
        return ProductArticles.builder()
                .id(id)
                .stock(stock)
                .build();
    }
}
