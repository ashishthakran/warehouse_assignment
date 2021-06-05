package com.ikea.wms.api.service.validator;

import com.ikea.wms.api.core.exceptions.DuplicateProductException;
import com.ikea.wms.api.core.model.product.ProductDto;

import java.util.Optional;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
public class ProductValidator {

    public static final String ERR_DUPLICATE_PRODUCT = "Product with %d already exists";

    public static void isDuplicate(Optional<ProductDto> productDto) throws DuplicateProductException {
        if(productDto.isPresent()) {
            throw new DuplicateProductException(String.format(ERR_DUPLICATE_PRODUCT, productDto.get().getId()));
        }
    }
}
