package config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import crm.controller.HomePageController;

@SpringBootApplication
@ComponentScan("crm")
@MapperScan("crm.mapper")
@EnableJpaRepositories("crm.repository")
@Import(AspectConfig.class)
public class CRMConfig {
	
	@Bean
	public DataSource datasrc() throws SQLException {
		DataSource ds = new EmbeddedDatabaseBuilder().addScript("classpath:potentialLeads.sql").build();
		ds.getConnection().setAutoCommit(true);
		return ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() throws SQLException {
		return new JdbcTemplate(datasrc());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setTypeAliasesPackage("crm.vo");
		sessionFactory.setDataSource(datasrc());
		return sessionFactory.getObject();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ds);
		em.setPackagesToScan("crm.vo");
		JpaVendorAdapter va = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(va);
		return em;
	}

}
