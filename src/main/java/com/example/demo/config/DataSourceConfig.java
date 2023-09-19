package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DataSourceConfig {

	// Spring JDBC �� �������ִ� DataSource
//	@Bean
//	public DataSource dataSource()
//	{
//		HikariDataSource dataSource = new HikariDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/daeguyo");
//		dataSource.setUsername("root");
//		dataSource.setPassword("1234");
//
//		return dataSource;
//	}

	// HikariCp DataSource
	@Bean
	public HikariDataSource dataSource()
	{
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/daeguyo");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}


}
