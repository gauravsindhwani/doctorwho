package com.econsult.util;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class EntityManagerUtil {

	public static final EntityManager entityManager = buildEntityManager();

	private static EntityManager buildEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(getDataSource());
		entityManager.setPackagesToScan("com.econsult.model");
		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties jpaProps = new Properties();
		jpaProps.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProps.put("hibernate.show_sql", true);
		entityManager.setJpaProperties(jpaProps);
		entityManager.afterPropertiesSet();
		return entityManager.nativeEntityManagerFactory.createEntityManager();
	}
	
	private static DataSource getDataSource(){
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUrl("jdbc:mysql://localhost/econsult");
		ds.setUser("root");
		ds.setPassword("root");
		return ds;
	}
}
