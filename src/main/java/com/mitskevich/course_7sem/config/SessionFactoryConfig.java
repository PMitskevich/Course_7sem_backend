package com.mitskevich.course_7sem.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
//public class SessionFactoryConfig {

//    @Value("${spring.datasource.driverClassName}")
//    private String driverClassName;
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String ddlAutoMode;
//    @Value("${spring.jpa.properties.hibernate.dialect}")
//    private String dialect;
//    private final String DDL_AUTO_PROPERTY_NAME = "spring.jpa.hibernate.ddl-auto";
//    private final String DIALECT_PROPERTY_NAME = "spring.jpa.properties.hibernate.dialect";

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
////        sessionFactory.setPackagesToScan(
////                {"com.baeldung.hibernate.bootstrap.model" });
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }

//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(driverClassName);
//        dataSourceBuilder.url(url);
//        dataSourceBuilder.username(username);
//        dataSourceBuilder.password(password);
//        return dataSourceBuilder.build();
//    }
//
//    private Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(DDL_AUTO_PROPERTY_NAME, ddlAutoMode);
//        hibernateProperties.setProperty(DIALECT_PROPERTY_NAME, dialect);
//
//        return hibernateProperties;
//    }
//}
