package br.com.yaman.quitanda.dao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author marcus.martins
 *
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
public class PersistenceContext {

    private static final String[] PROPERTY_NAME_ENTITY_PACKAGES = {"br.com.yaman.quitanda.dao.entity"};

    /**
     * Creates and configures the HikariCP datasource bean.
     * @param environment The runtime environment of  our application.
     */
    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment environment) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getRequiredProperty("db.driver"));
        hikariConfig.setJdbcUrl(environment.getRequiredProperty("db.url"));
        hikariConfig.setUsername(environment.getRequiredProperty("db.user"));
        hikariConfig.setPassword(environment.getRequiredProperty("db.pass"));
        hikariConfig.setConnectionTimeout(10000);
        hikariConfig.setIdleTimeout(60000);
        hikariConfig.setMaxLifetime(60000);
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("db.pool.size")));
        return new HikariDataSource(hikariConfig);
    }
    @Bean
    NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    /**
     * Creates the bean that creates the JPA entity manager factory.
     * @param dataSource    The datasource that provides the database connections.
     * @param env           The runtime environment of  our application.
     */
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITY_PACKAGES);

        Properties jpaProperties = new Properties();	
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.jdbc.batch_size", env.getRequiredProperty("hibernate.jdbc.batch_size"));
        jpaProperties.put("hibernate.jdbc.fatch_size", env.getRequiredProperty("hibernate.jdbc.fatch_size"));
        jpaProperties.put("hibernate.order_inserts", env.getRequiredProperty("hibernate.order_inserts"));
        jpaProperties.put("hibernate.order_updates", env.getRequiredProperty("hibernate.order_updates"));
        jpaProperties.put("hibernate.use_sql_comments", env.getRequiredProperty("hibernate.use_sql_comments"));
        jpaProperties.put("hibernate.id.new_generator_mapping", env.getRequiredProperty("hibernate.id.new_generator_mapping"));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    /**
     * Creates the transaction manager bean that integrates the used JPA provider with the
     * Spring transaction mechanism.
     * @param entityManagerFactory  The used JPA entity manager factory.
     */
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }



}
