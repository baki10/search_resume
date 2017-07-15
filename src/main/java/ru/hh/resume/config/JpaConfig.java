package ru.hh.resume.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("ru.hh.resume")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class JpaConfig {

  @Value("${jdbc.driverClassName}")
  private String driverClassName;
  @Value("${jdbc.url}")
  private String url;
  @Value("${jdbc.username}")
  private String username;
  @Value("${jdbc.password}")
  private String password;

  @Value("${hibernate.dialect}")
  private String hibernateDialect;
  @Value("${hibernate.show_sql}")
  private String hibernateShowSql;
  @Value("${hibernate.hbm2ddl.auto}")
  private String hibernateHbm2ddlAuto;

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory sessionFactory(DataSource dataSource) {
    return new LocalSessionFactoryBuilder(dataSource)
        .scanPackages("ru.hh.resume.model")
        .addProperties(getHibernateProperties())
        .buildSessionFactory();
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", hibernateDialect);
    properties.put("hibernate.show_sql", hibernateShowSql);
    properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
    return properties;
  }

  @Autowired
  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }
}