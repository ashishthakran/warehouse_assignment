package com.ikea.wms.api.web.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    private BigInteger id;

    @JsonProperty("name")
    @NotBlank(message = "Product name can't be blank.")
    @Size(min = 1, max = 200, message = "Product name can't be more than 200 characters")
    private String name;

    @JsonProperty("price")
    @NotNull(message = "Product price can't be blank.")
    private BigDecimal price;

    @JsonProperty("contain_articles")
    private Set<ProductArticle> articles;
}
