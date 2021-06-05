package com.ikea.wms.api.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@ToString
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractBaseDto implements Serializable {

    @EqualsAndHashCode.Include
    private BigInteger id;
}
