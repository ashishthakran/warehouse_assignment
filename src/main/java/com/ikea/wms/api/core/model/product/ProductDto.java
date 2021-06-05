package com.ikea.wms.api.core.model.product;

import com.ikea.wms.api.core.model.AbstractBaseDto;
import com.ikea.wms.api.core.model.article.ArticleDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
public class ProductDto extends AbstractBaseDto {

    @EqualsAndHashCode.Include
    @NotBlank(message = "Product name can't be blank.")
    @Size(min = 1, max = 200, message = "Product name can't be more than 200 characters")
    private String name;

    @EqualsAndHashCode.Include
    @NotNull(message = "Product price can't be blank.")
    private BigDecimal price;

    private Set<ArticleDto> articles;
}
