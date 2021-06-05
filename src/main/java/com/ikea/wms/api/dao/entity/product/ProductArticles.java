package com.ikea.wms.api.dao.entity.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @version 1.0
 * @author Aashish Thakran
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@Table(name = "wms_product_articles")
public class ProductArticles {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ProductArticleId id;

    @NotNull(message = "Article stock can't be blank.")
    @Column(name = "stock", nullable = false)
    private BigInteger stock;
}
