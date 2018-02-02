package com.wat.foodmanager.jpa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.wat.foodmanager.jpa")
@PropertySource({"classpath:persistance.properties"})
@EnableJpaRepositories(basePackages = "com.wat.foodmanager.jpa.repositories")
public class JpaContextConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getProperty("db.driver"));
        driverManagerDataSource.setUsername(env.getProperty("db.username"));
        driverManagerDataSource.setPassword(env.getProperty("db.password"));
        driverManagerDataSource.setUrl(env.getProperty("db.url"));
        return driverManagerDataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabase(Database.MYSQL);
        bean.setGenerateDdl(true);
        return bean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan("com.wat.foodmanager.jpa");
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
