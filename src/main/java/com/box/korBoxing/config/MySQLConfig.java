package com.box.korBoxing.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value = "com.box.korBoxing.dao", sqlSessionFactoryRef = "MySQLSessionFactory")
@EnableTransactionManagement
public class MySQLConfig {
	
    @Value("${mybatis.config-location}")
    private String mybatisConfigLocaton;
    @Value("${mybatis.mysql.mapper-location}")
    private String mybatisMapperLocaton;
	
	@Bean(name = "MySQLDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.mysql.datasource")
	public DataSource getMySQLDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "MySQLSessionFactory")
	@Primary
	public SqlSessionFactory getMySQLSessionFactory(@Qualifier("MySQLDataSource") DataSource mysqlDataSource, 
												 							   	  ApplicationContext context) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlDataSource);
        sqlSessionFactoryBean.setConfigLocation(context.getResource(mybatisConfigLocaton));
        sqlSessionFactoryBean.setMapperLocations(context.getResources(mybatisMapperLocaton));
        
        return sqlSessionFactoryBean.getObject();
	}
	
    @Bean(name = "MySQLSessionTemplate")
    @Primary
    public SqlSessionTemplate getMySQLSessionTemplate(SqlSessionFactory mysqlSessionFactory) {
        return new SqlSessionTemplate(mysqlSessionFactory);
    }

}
