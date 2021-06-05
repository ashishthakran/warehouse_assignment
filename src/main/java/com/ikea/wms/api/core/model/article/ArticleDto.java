package com.ikea.wms.api.core.model.article;

import com.ikea.wms.api.core.model.AbstractBaseDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@ToString
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
public class ArticleDto extends AbstractBaseDto {

    @Size(min = 1, max = 200, message = "Article name can't be more than 200 characters")
    @NotBlank(message = "Article name can't be blank.")
    private String name;

    @NotNull(message = "Article stock can't be blank.")
    private BigInteger stock;
}
