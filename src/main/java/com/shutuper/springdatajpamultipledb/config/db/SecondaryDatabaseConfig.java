package com.shutuper.springdatajpamultipledb.config.db;

import com.shutuper.springdatajpamultipledb.MultipleDbApplication;
import com.shutuper.springdatajpamultipledb.config.db.filter.SecondaryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
		basePackageClasses = MultipleDbApplication.class,
		transactionManagerRef = "secondaryTransactionManager",
		entityManagerFactoryRef = "secondaryEntityManagerFactory",
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = SecondaryRepository.class)
)
public class SecondaryDatabaseConfig {

	@Bean(name = "secondaryDataBase")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataBase() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(secondaryDataBase())
				.packages(MultipleDbApplication.class)
				.persistenceUnit("secondaryDataBase")
				.build();
	}

	@Bean(name = "secondaryTransactionManager")
	public JpaTransactionManager secondaryTransactionManager(@Qualifier("secondaryEntityManagerFactory") final
	                                                        LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}

}
