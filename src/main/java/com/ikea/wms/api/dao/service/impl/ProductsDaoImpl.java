package com.ikea.wms.api.dao.service.impl;

import com.ikea.wms.api.core.exceptions.ArticleNotFoundException;
import com.ikea.wms.api.core.exceptions.InsufficientInventoryException;
import com.ikea.wms.api.core.model.article.ArticleDto;
import com.ikea.wms.api.core.model.product.ProductDto;
import com.ikea.wms.api.dao.entity.product.ProductArticles;
import com.ikea.wms.api.dao.mapper.ProductArticleMapper;
import com.ikea.wms.api.dao.repository.ProductArticlesRepository;
import com.ikea.wms.api.dao.service.IArticlesDao;
import com.ikea.wms.api.dao.service.IProductDao;
import com.ikea.wms.api.dao.entity.product.Products;
import com.ikea.wms.api.dao.mapper.ProductMapper;
import com.ikea.wms.api.dao.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
class ProductsDaoImpl implements IProductDao {

    private final ProductsRepository productsRepository;
    private final IArticlesDao articlesDao;
    private final ProductArticlesRepository productArticlesRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<ProductDto> createProducts(List<ProductDto> productDtoList) throws ArticleNotFoundException, InsufficientInventoryException {
        return productDtoList.stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDto save(ProductDto productDto) {
        Products product = ProductMapper.mapToEntity(productDto);
        productsRepository.save(product);
        return ProductMapper.mapToDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto get(BigInteger id) {
        return productsRepository.findById(id)
                .map(ProductMapper::mapToDto)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        List<Products> products = productsRepository.findAll();
        return products.stream()
                .map(ProductMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDto createProduct(ProductDto productDto) throws ArticleNotFoundException, InsufficientInventoryException {
        ProductDto product = save(productDto);
        productDto.getArticles().forEach(article -> {
            ArticleDto articleDto = articlesDao.updateInventory(article.getId(), article.getStock());
            ProductArticles productArticle = ProductArticleMapper.mapToEntity(articleDto, product);
            productArticlesRepository.save(productArticle);
        });

        return get(product.getId());
    }
}
