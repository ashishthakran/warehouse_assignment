package com.ikea.wms.api.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@EntityScan(value = {"com.ikea.wms.api.dao.entity"})
@EnableJpaRepositories(value = {"com.ikea.wms.api.dao.repository"})
public class DaoConfiguration {
}
