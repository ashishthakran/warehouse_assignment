package com.ikea.wms.api.dao.repository;

import com.ikea.wms.api.dao.entity.product.ProductArticleId;
import com.ikea.wms.api.dao.entity.product.ProductArticles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductArticlesRepository extends JpaRepository<ProductArticles, ProductArticleId> {
}
