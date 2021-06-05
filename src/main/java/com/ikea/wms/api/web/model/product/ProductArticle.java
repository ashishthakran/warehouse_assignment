package com.ikea.wms.api.web.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductArticle {

    @JsonProperty("art_id")
    @EqualsAndHashCode.Include
    @NotNull(message = "Article id can't be blank.")
    private BigInteger id;

    @JsonProperty("amount_of")
    @NotNull(message = "Article stock can't be blank.")
    private BigInteger stock;
}
