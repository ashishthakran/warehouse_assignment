package com.ikea.wms.api.dao.entity.product;

import com.ikea.wms.api.dao.entity.AbstractBaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

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
@Table(name = "wms_product_master")
public class Products extends AbstractBaseEntity {

    @NotNull
    @EqualsAndHashCode.Include
    @Column(name = "article_name", nullable = false, length = 200)
    private String name;

    @NotNull
    @EqualsAndHashCode.Include
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "id.product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductArticles> articles;
}
