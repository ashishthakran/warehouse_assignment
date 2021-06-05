package com.ikea.wms.api.web.model.article;

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
@EqualsAndHashCode
public class Article {

    @JsonProperty("art_id")
    private BigInteger id;

    @JsonProperty("name")
    @NotBlank(message = "Article name can't be blank.")
    @Size(min = 1, max = 200, message = "Article name can't be more than 200 characters")
    private String name;

    @JsonProperty("stock")
    @NotNull(message = "Article stock can't be blank.")
    private BigInteger stock;
}
