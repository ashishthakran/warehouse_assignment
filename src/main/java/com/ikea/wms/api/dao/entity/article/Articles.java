package com.ikea.wms.api.dao.entity.article;

import com.ikea.wms.api.dao.entity.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
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
@SuperBuilder(toBuilder = true)
@Table(name = "wms_article_master")
public class Articles extends AbstractBaseEntity {

    @NotNull
    @Column(name = "article_name", nullable = false, length = 200)
    private String name;

    @NotNull
    @Column(name = "available_stock", nullable = false)
    private BigInteger availableStock;
}
