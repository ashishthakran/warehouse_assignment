package com.ikea.wms.api.dao.entity.product;

import com.ikea.wms.api.dao.entity.article.Articles;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductArticleId implements Serializable {

    @ManyToOne
    @NotNull(message = "Article can't be blank.")
    @JoinColumn(name = "article_id", nullable = false, updatable = false)
    private Articles article;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Product can't be blank.")
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Products product;
}
