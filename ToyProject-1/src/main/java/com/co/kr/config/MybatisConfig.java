package com.co.kr.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(
		basePackages = {"com.co.kr.mapper"},
		annotationClass = org.apache.ibatis.annotations.Mapper.class,
		sqlSessionFactoryRef = "sqlSessionFactory" )
public class MybatisConfig {

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception{
		
		//Bean으로 등록할 SqlSessionFactory 객체 생성
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); 
		//sqlSessionFactoryBean에 dataSource정보를 set함 (Datasource정보는 application.yml애 명시함)
		sqlSessionFactoryBean.setDataSource(dataSource);
		//Mapper.xml 파일의 위치를 명시
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*Mapper.xml"));
		return sqlSessionFactoryBean.getObject();
	}
}
