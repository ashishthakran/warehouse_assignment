package com.ikea.wms.api.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Data
@ToString
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractBaseEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected BigInteger id;
}
