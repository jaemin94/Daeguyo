package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
public class TxConfig {



    @Autowired
    HikariDataSource dataSource;

    @Bean
    public DataSourceTransactionManager transactionManager()
    {
        System.out.println("TX dataSource2 : " + dataSource.toString());
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }
}
