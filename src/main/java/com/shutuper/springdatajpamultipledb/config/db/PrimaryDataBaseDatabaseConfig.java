package com.shutuper.springdatajpamultipledb.config.db;

import com.shutuper.springdatajpamultipledb.MultipleDbApplication;
import com.shutuper.springdatajpamultipledb.config.db.filter.PrimaryDataBaseRepository;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
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
		transactionManagerRef = "primaryDataBaseTransactionManager",
		entityManagerFactoryRef = "primaryDataBaseEntityManagerFactory",
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = PrimaryDataBaseRepository.class)
)
public class PrimaryDataBaseDatabaseConfig {

	@Primary
	@Bean(name = "primaryDataBaseDataBase")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataBaseDataBase() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "primaryDataBaseEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean navierreEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(primaryDataBaseDataBase())
				.packages(MultipleDbApplication.class)
				.persistenceUnit("primaryDataBaseDataBase")
				.build();
	}

	@Primary
	@Bean(name = "primaryDataBaseTransactionManager")
	public JpaTransactionManager navierreTransactionManager(@Qualifier("primaryDataBaseEntityManagerFactory") final
															LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.primary.liquibase")
	public LiquibaseProperties primaryLiquibaseProperties() {
		return new LiquibaseProperties();
	}

	@Bean
	@Primary
	public SpringLiquibase primaryLiquibase() {
		return springLiquibase(primaryDataBaseDataBase(), primaryLiquibaseProperties());
	}

	private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog(properties.getChangeLog());
		liquibase.setContexts(properties.getContexts());
		liquibase.setDefaultSchema(properties.getDefaultSchema());
		liquibase.setDropFirst(properties.isDropFirst());
		liquibase.setShouldRun(properties.isEnabled());
		liquibase.setLabelFilter(properties.getLabelFilter());
		liquibase.setChangeLogParameters(properties.getParameters());
		liquibase.setRollbackFile(properties.getRollbackFile());
		return liquibase;
	}

}
